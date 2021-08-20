package com.hippalus.exchangeapi.infra.common.configuration;

import com.hippalus.exchangeapi.infra.common.rest.RestTemplateResponseErrorHandler;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HeaderElement;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class RestConfiguration {

  private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
    restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
    return restTemplate;
  }

  @Bean
  public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
    var factory = new HttpComponentsClientHttpRequestFactory();
    factory.setHttpClient(httpClient());
    return factory;

  }

  private CloseableHttpClient httpClient() {
    long period = 30000 / 2;
    var connectionManager = poolingConnectionManager();
    executor.scheduleAtFixedRate(closeExpiredConnections(connectionManager), 30000, period, TimeUnit.MILLISECONDS);
    return HttpClients.custom()
        .setDefaultRequestConfig(requestConfig())
        .setConnectionManager(connectionManager)
        .setKeepAliveStrategy(connectionKeepAliveStrategy())
        .build();
  }

  private RequestConfig requestConfig() {
    return RequestConfig.custom()
        .setConnectionRequestTimeout(60000)
        .setConnectTimeout(60000)
        .setSocketTimeout(60000)
        .build();
  }

  @Bean
  public PoolingHttpClientConnectionManager poolingConnectionManager() {
    var sslContextBuilder = SSLContextBuilder.create();
    try {
      sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
    } catch (NoSuchAlgorithmException | KeyStoreException e) {
      e.printStackTrace();
    }

    SSLConnectionSocketFactory sslConnectionSocketFactory = getSslConnectionSocketFactory(sslContextBuilder);

    var connSocketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create();
    connSocketFactoryRegistry.register("http", new PlainConnectionSocketFactory());

    if (Objects.nonNull(sslConnectionSocketFactory)) {
      connSocketFactoryRegistry.register("https", sslConnectionSocketFactory);
    }

    var poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(connSocketFactoryRegistry.build());
    poolingHttpClientConnectionManager.setMaxTotal(20);

    return poolingHttpClientConnectionManager;
  }

  @Bean
  public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
    return (httpResponse, httpContext) -> {
      var it = new BasicHeaderElementIterator(httpResponse.headerIterator(HTTP.CONN_KEEP_ALIVE));
      while (it.hasNext()) {
        HeaderElement headerElement = it.nextElement();
        if (headerElement.getName() != null && headerElement.getValue().equalsIgnoreCase("timeout")) {
          return Long.parseLong(headerElement.getValue()) * 1000;
        }
      }
      return 20000;
    };
  }

  @Nullable
  private SSLConnectionSocketFactory getSslConnectionSocketFactory(SSLContextBuilder sslContextBuilder) {
    try {
      return new SSLConnectionSocketFactory(sslContextBuilder.build());
    } catch (NoSuchAlgorithmException | KeyManagementException e) {
      e.printStackTrace();
    }
    return null;
  }


  @NotNull
  private Runnable closeExpiredConnections(PoolingHttpClientConnectionManager cm) {
    return () -> {
      cm.closeExpiredConnections();
      cm.closeIdleConnections(10000, TimeUnit.MILLISECONDS);
      log.trace("Expired and idle connections closed");
    };
  }

}
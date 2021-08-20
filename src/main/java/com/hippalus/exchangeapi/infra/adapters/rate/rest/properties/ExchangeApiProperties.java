package com.hippalus.exchangeapi.infra.adapters.rate.rest.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConditionalOnProperty(name = "adapters.exchange-rate.enabled", havingValue = "true")
@ConfigurationProperties(prefix = "adapters.exchange-rate")
public class ExchangeApiProperties {

  private String accessKey;
  private String baseUrl;
  private String path;

}

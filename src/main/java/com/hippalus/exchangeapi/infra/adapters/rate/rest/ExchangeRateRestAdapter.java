package com.hippalus.exchangeapi.infra.adapters.rate.rest;

import com.hippalus.exchangeapi.domain.common.exception.ExchangeApiBusinessException;
import com.hippalus.exchangeapi.domain.rate.model.ExchangeRate;
import com.hippalus.exchangeapi.domain.rate.port.ExchangeRatePort;
import com.hippalus.exchangeapi.domain.rate.usecase.RetrieveExchangeRate;
import com.hippalus.exchangeapi.infra.adapters.rate.rest.dto.ExchangeRateThirdPartyResponse;
import com.hippalus.exchangeapi.infra.adapters.rate.rest.properties.ExchangeApiProperties;
import com.hippalus.exchangeapi.infra.common.rest.HttpHeaderUtil;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "adapters.exchange-rate.enabled", havingValue = "true")
public class ExchangeRateRestAdapter implements ExchangeRatePort {

  private final RestTemplate restTemplate;
  private final ExchangeApiProperties exchangeApiProperties;

  @Override
  public ExchangeRate retrieve(RetrieveExchangeRate retrieveExchangeRate) {

    HttpEntity<?> entity = new HttpEntity<>(HttpHeaderUtil.defaultHttpHeader());
    UriComponentsBuilder builder = prepareUri(retrieveExchangeRate);

    var response = restTemplate.exchange(
        builder.toUriString(),
        HttpMethod.GET,
        entity,
        ExchangeRateThirdPartyResponse.class);

    var responseBody = response.getBody();

    if (Objects.isNull(responseBody) || !responseBody.isSuccess()) {
      throw new ExchangeApiBusinessException("exchangeRate.adapter.restCall.failedResponse");
    }

    return responseBody.toModel();
  }

  @NotNull
  private UriComponentsBuilder prepareUri(RetrieveExchangeRate retrieveExchangeRate) {
    return UriComponentsBuilder.fromHttpUrl(exchangeApiProperties.getBaseUrl())
        .queryParam("access_key", exchangeApiProperties.getAccessKey())
        .queryParam("base", retrieveExchangeRate.getSourceCurrency())
        .queryParam("symbols", retrieveExchangeRate.getTargetCurrency());
  }
}

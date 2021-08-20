package com.hippalus.exchangeapi.infra.adapters.rate.rest.dto;

import com.hippalus.exchangeapi.domain.rate.model.ExchangeRate;
import java.math.BigDecimal;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateResponse {

  private String sourceCurrency;
  private Map<String, BigDecimal> rates;

  public static ExchangeRateResponse fromModel(ExchangeRate exchangeRate) {
    return ExchangeRateResponse.builder()
        .sourceCurrency(exchangeRate.getSourceCurrency())
        .rates(exchangeRate.getRates())
        .build();
  }
}

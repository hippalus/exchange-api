package com.hippalus.exchangeapi.domain.rate.model;

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
public class ExchangeRate {

  private String sourceCurrency;
  @Builder.Default
  private Map<String, BigDecimal> rates = Map.of();

  public BigDecimal get(String currency) {
    return rates.get(currency);
  }
}

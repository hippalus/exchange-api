package com.hippalus.exchangeapi.infra.adapters.rate.rest.dto;

import com.hippalus.exchangeapi.domain.rate.model.ExchangeRate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateThirdPartyResponse {

  private boolean success;
  private int timestamp;
  private String base;
  private LocalDate date;
  private Map<String, BigDecimal> rates;

  public ExchangeRate toModel() {
    return ExchangeRate.builder()
        .sourceCurrency(this.base)
        .rates(this.rates)
        .build();
  }
}

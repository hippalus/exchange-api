package com.hippalus.exchangeapi.domain.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateCalculator {

  public BigDecimal amount(BigDecimal amount, BigDecimal rate) {
    return amount.multiply(rate).setScale(4, RoundingMode.HALF_EVEN);
  }
}

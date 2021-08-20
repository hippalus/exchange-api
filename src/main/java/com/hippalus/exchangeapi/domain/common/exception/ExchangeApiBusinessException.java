package com.hippalus.exchangeapi.domain.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeApiBusinessException extends RuntimeException {

  private final String key;
  private final String[] args;

  public ExchangeApiBusinessException(String key) {
    super(key);
    this.key = key;
    args = new String[0];
  }

  public ExchangeApiBusinessException(String key, String... args) {
    super(key);
    this.key = key;
    this.args = args;
  }

}

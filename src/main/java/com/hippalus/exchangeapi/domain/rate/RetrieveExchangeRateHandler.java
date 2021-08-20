package com.hippalus.exchangeapi.domain.rate;

import com.hippalus.exchangeapi.domain.common.usecase.UseCaseHandler;
import com.hippalus.exchangeapi.domain.rate.model.ExchangeRate;
import com.hippalus.exchangeapi.domain.rate.port.ExchangeRatePort;
import com.hippalus.exchangeapi.domain.rate.usecase.RetrieveExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RetrieveExchangeRateHandler implements UseCaseHandler<ExchangeRate, RetrieveExchangeRate> {

  private final ExchangeRatePort exchangeRatePort;

  @Override
  public ExchangeRate handle(RetrieveExchangeRate useCase) {
    return exchangeRatePort.retrieve(useCase);
  }
}

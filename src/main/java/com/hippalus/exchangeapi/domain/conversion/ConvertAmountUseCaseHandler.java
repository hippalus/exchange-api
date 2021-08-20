package com.hippalus.exchangeapi.domain.conversion;

import com.hippalus.exchangeapi.domain.common.usecase.UseCaseHandler;
import com.hippalus.exchangeapi.domain.common.util.ExchangeRateCalculator;
import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import com.hippalus.exchangeapi.domain.conversion.usecase.ConvertAmount;
import com.hippalus.exchangeapi.domain.rate.model.ExchangeRate;
import com.hippalus.exchangeapi.domain.rate.port.ExchangeRatePort;
import com.hippalus.exchangeapi.domain.rate.usecase.RetrieveExchangeRate;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConvertAmountUseCaseHandler implements UseCaseHandler<Conversion, ConvertAmount> {

  private final ExchangeRatePort exchangeRatePort;
  private final ExchangeRateCalculator exchangeRateCalculator;

  @Override
  public Conversion handle(ConvertAmount useCase) {
    RetrieveExchangeRate retrieveExchangeRate = RetrieveExchangeRate.builder()
        .sourceCurrency(useCase.getFrom())
        .targetCurrency(useCase.getTo())
        .build();

    ExchangeRate exchangeRate = exchangeRatePort.retrieve(retrieveExchangeRate);
    BigDecimal rateOfTarget = exchangeRate.get(useCase.getTo());
    BigDecimal result = exchangeRateCalculator.amount(useCase.getAmount(), rateOfTarget);

    return Conversion.builder()
        .from(useCase.getFrom())
        .to(useCase.getTo())
        .amount(useCase.getAmount())
        .result(result)
        .build();
  }
}

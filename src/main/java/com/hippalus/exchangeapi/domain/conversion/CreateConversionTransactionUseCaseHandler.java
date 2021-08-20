package com.hippalus.exchangeapi.domain.conversion;

import com.hippalus.exchangeapi.domain.common.usecase.UseCaseHandler;
import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import com.hippalus.exchangeapi.domain.conversion.port.ConversionTransactionPort;
import com.hippalus.exchangeapi.domain.conversion.usecase.CreateConversionTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateConversionTransactionUseCaseHandler implements UseCaseHandler<Conversion, CreateConversionTransaction> {

  private final ConversionTransactionPort conversionPort;

  @Override
  public Conversion handle(CreateConversionTransaction useCase) {
    return conversionPort.createTransaction(useCase);
  }
}

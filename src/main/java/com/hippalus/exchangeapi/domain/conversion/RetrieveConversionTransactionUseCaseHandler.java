package com.hippalus.exchangeapi.domain.conversion;

import com.hippalus.exchangeapi.domain.common.usecase.UseCaseHandler;
import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import com.hippalus.exchangeapi.domain.conversion.port.ConversionTransactionPort;
import com.hippalus.exchangeapi.domain.conversion.usecase.ConversionTransactionRetrieve;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RetrieveConversionTransactionUseCaseHandler implements
    UseCaseHandler<List<Conversion>, ConversionTransactionRetrieve> {

  private final ConversionTransactionPort conversionPort;

  @Override
  public List<Conversion> handle(ConversionTransactionRetrieve useCase) {
    return conversionPort.retrieve(useCase);
  }
}

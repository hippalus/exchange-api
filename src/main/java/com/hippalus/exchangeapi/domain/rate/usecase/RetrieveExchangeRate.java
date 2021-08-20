package com.hippalus.exchangeapi.domain.rate.usecase;

import com.hippalus.exchangeapi.domain.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RetrieveExchangeRate implements UseCase {

  private String sourceCurrency;
  private String targetCurrency;

}
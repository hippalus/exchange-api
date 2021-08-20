package com.hippalus.exchangeapi.domain.conversion.usecase;

import com.hippalus.exchangeapi.domain.common.model.UseCase;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConversionTransactionRetrieve implements UseCase {

  private Long transactionId;
  private LocalDateTime dateTime;
  private Integer page;
  private Integer size;


}

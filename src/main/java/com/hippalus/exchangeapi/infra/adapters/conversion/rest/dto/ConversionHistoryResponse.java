package com.hippalus.exchangeapi.infra.adapters.conversion.rest.dto;

import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversionHistoryResponse {

  private Long transactionId;
  private String from;
  private String to;
  private BigDecimal amount;
  private BigDecimal result;
  private LocalDateTime dateTime;

  public static ConversionHistoryResponse fromModel(Conversion conversion) {
    return ConversionHistoryResponse.builder()
        .from(conversion.getFrom())
        .to(conversion.getTo())
        .amount(conversion.getAmount())
        .result(conversion.getResult())
        .dateTime(conversion.getDateTime())
        .transactionId(conversion.getTransactionId())
        .build();
  }
}

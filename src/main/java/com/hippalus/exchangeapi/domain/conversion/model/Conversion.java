package com.hippalus.exchangeapi.domain.conversion.model;

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
public class Conversion {

  private Long transactionId;
  private String from;
  private String to;
  private BigDecimal amount;
  private BigDecimal result;
  private LocalDateTime dateTime;
}

package com.hippalus.exchangeapi.domain.conversion.usecase;

import com.hippalus.exchangeapi.domain.common.model.UseCase;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConvertAmount implements UseCase {

  private String from;
  private String to;
  private BigDecimal amount;
}

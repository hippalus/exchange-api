package com.hippalus.exchangeapi.infra.adapters.conversion.rest.dto;

import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversionResponse {

  private BigDecimal result;

  public static ConversionResponse fromModel(Conversion conversion) {
    return new ConversionResponse(conversion.getResult());
  }
}

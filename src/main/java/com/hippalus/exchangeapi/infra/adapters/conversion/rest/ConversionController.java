package com.hippalus.exchangeapi.infra.adapters.conversion.rest;

import com.hippalus.exchangeapi.domain.common.usecase.UseCaseHandler;
import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import com.hippalus.exchangeapi.domain.conversion.usecase.ConvertAmount;
import com.hippalus.exchangeapi.domain.conversion.usecase.CreateConversionTransaction;
import com.hippalus.exchangeapi.infra.adapters.conversion.rest.dto.ConversionResponse;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/convert")
public class ConversionController {

  private final UseCaseHandler<Conversion, ConvertAmount> convertAmountUseCaseHandler;
  private final UseCaseHandler<Conversion, CreateConversionTransaction> createConversionTransactionUseCaseHandler;

  @GetMapping
  public ResponseEntity<ConversionResponse> convert(
      @RequestParam("from") String from,
      @RequestParam("to") String to,
      @RequestParam("amount") BigDecimal amount) {

    var convertAmount = ConvertAmount.builder()
        .from(from)
        .to(to)
        .amount(amount)
        .build();

    var conversion = convertAmountUseCaseHandler.handle(convertAmount);
    //TODO: async or aspect based
    createTransaction(conversion);

    return ResponseEntity.ok(ConversionResponse.fromModel(conversion));
  }

  private void createTransaction(Conversion conversion) {
    CreateConversionTransaction createConversionTransaction = CreateConversionTransaction.builder()
        .from(conversion.getFrom())
        .to(conversion.getTo())
        .amount(conversion.getAmount())
        .result(conversion.getResult())
        .build();
    createConversionTransactionUseCaseHandler.handle(createConversionTransaction);
  }


}

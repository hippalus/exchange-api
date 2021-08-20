package com.hippalus.exchangeapi.infra.adapters.conversion.rest;

import com.hippalus.exchangeapi.domain.common.usecase.UseCaseHandler;
import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import com.hippalus.exchangeapi.domain.conversion.usecase.ConversionTransactionRetrieve;
import com.hippalus.exchangeapi.infra.adapters.conversion.rest.dto.ConversionHistoryResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/conversion")
public class ConversionTransactionController {

  private final UseCaseHandler<List<Conversion>, ConversionTransactionRetrieve> transactionRetrieveUseCaseHandler;

  @GetMapping
  public ResponseEntity<List<ConversionHistoryResponse>> findByDateOrId(
      @RequestParam(value = "txnDate", required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime transactionDate,
      @RequestParam(value = "txnId", required = false) Long transactionId,
      @RequestParam("page") Integer page,
      @RequestParam("size") Integer size) {

    var transactionRetrieve = ConversionTransactionRetrieve.builder()
        .dateTime(transactionDate)
        .transactionId(transactionId)
        .page(page)
        .size(size)
        .build();

    var history = transactionRetrieveUseCaseHandler.handle(transactionRetrieve)
        .stream()
        .map(ConversionHistoryResponse::fromModel)
        .toList();

    return ResponseEntity.ok(history);

  }

}

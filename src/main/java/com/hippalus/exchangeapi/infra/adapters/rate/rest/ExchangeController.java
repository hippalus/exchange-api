package com.hippalus.exchangeapi.infra.adapters.rate.rest;

import com.hippalus.exchangeapi.domain.common.usecase.UseCaseHandler;
import com.hippalus.exchangeapi.domain.rate.model.ExchangeRate;
import com.hippalus.exchangeapi.domain.rate.usecase.RetrieveExchangeRate;
import com.hippalus.exchangeapi.infra.adapters.rate.rest.dto.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/exchange")
public class ExchangeController {

  private final UseCaseHandler<ExchangeRate, RetrieveExchangeRate> retrieveExchangeRateUseCaseHandler;

  @GetMapping
  public ResponseEntity<ExchangeRateResponse> getExchangeRate(
      @RequestParam("sourceCurrency") String sourceCurrency,
      @RequestParam("targetCurrency") String targetCurrency) {

    var exchangeUseCase = RetrieveExchangeRate.builder()
        .sourceCurrency(sourceCurrency)
        .targetCurrency(targetCurrency)
        .build();

    var exchangeRate = retrieveExchangeRateUseCaseHandler.handle(exchangeUseCase);
    return ResponseEntity.ok(ExchangeRateResponse.fromModel(exchangeRate));

  }


}

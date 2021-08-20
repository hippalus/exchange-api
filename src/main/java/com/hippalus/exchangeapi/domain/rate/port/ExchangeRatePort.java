package com.hippalus.exchangeapi.domain.rate.port;

import com.hippalus.exchangeapi.domain.rate.model.ExchangeRate;
import com.hippalus.exchangeapi.domain.rate.usecase.RetrieveExchangeRate;

public interface ExchangeRatePort {

  ExchangeRate retrieve(RetrieveExchangeRate retrieveExchangeRate);
}

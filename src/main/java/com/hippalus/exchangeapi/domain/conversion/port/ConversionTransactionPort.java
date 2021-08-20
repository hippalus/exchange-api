package com.hippalus.exchangeapi.domain.conversion.port;

import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import com.hippalus.exchangeapi.domain.conversion.usecase.ConversionTransactionRetrieve;
import com.hippalus.exchangeapi.domain.conversion.usecase.CreateConversionTransaction;
import java.util.List;

public interface ConversionTransactionPort {


  Conversion createTransaction(CreateConversionTransaction conversionTransaction);

  List<Conversion> retrieve(ConversionTransactionRetrieve transactionRetrieve);


}

package com.hippalus.exchangeapi.infra.adapters.conversion.jpa;

import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import com.hippalus.exchangeapi.domain.conversion.port.ConversionTransactionPort;
import com.hippalus.exchangeapi.domain.conversion.usecase.ConversionTransactionRetrieve;
import com.hippalus.exchangeapi.domain.conversion.usecase.CreateConversionTransaction;
import com.hippalus.exchangeapi.infra.adapters.conversion.jpa.entity.ConversionEntity;
import com.hippalus.exchangeapi.infra.adapters.conversion.jpa.repository.ConversionJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversionDataAdapter implements ConversionTransactionPort {

  private final ConversionJpaRepository conversionJpaRepository;


  @Override
  public Conversion createTransaction(CreateConversionTransaction conversionTransaction) {
    ConversionEntity entity = toEntity(conversionTransaction);
    return conversionJpaRepository.save(entity).toModel();
  }

  @Override
  public List<Conversion> retrieve(ConversionTransactionRetrieve transactionRetrieve) {
    var pageRequest = PageRequest.of(transactionRetrieve.getPage(), transactionRetrieve.getSize());
    Page<ConversionEntity> result = conversionJpaRepository.findAllByIdOrCreatedAt(
        transactionRetrieve.getTransactionId(),
        transactionRetrieve.getDateTime(),
        pageRequest);
    return result.stream().map(ConversionEntity::toModel).toList();
  }

  private ConversionEntity toEntity(CreateConversionTransaction conversionTransaction) {
    return ConversionEntity.builder()
        .source(conversionTransaction.getFrom())
        .target(conversionTransaction.getTo())
        .amount(conversionTransaction.getAmount())
        .result(conversionTransaction.getResult())
        .build();
  }
}

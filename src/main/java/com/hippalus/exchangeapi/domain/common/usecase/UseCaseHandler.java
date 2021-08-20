package com.hippalus.exchangeapi.domain.common.usecase;

import com.hippalus.exchangeapi.domain.common.model.UseCase;

public interface UseCaseHandler<T, U extends UseCase> {

  T handle(U useCase);

}

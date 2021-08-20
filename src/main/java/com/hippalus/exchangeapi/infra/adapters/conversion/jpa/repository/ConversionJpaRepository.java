package com.hippalus.exchangeapi.infra.adapters.conversion.jpa.repository;

import com.hippalus.exchangeapi.infra.adapters.conversion.jpa.entity.ConversionEntity;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionJpaRepository extends JpaRepository<ConversionEntity, Long>,
    PagingAndSortingRepository<ConversionEntity, Long> {

  Page<ConversionEntity> findAllByIdOrCreatedAt(Long id, LocalDateTime txnDate, Pageable pageable);

}

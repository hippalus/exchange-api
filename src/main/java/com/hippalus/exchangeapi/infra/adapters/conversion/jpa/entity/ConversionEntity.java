package com.hippalus.exchangeapi.infra.adapters.conversion.jpa.entity;

import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import com.hippalus.exchangeapi.infra.common.jpa.entity.AbstractAuditingEntity;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conversion")
public class ConversionEntity extends AbstractAuditingEntity {

  @Column(nullable = false)
  private String source;

  @Column(nullable = false)
  private String target;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(nullable = false)
  private BigDecimal result;


  @Override
  public Conversion toModel() {
    return Conversion.builder()
        .from(source)
        .to(target)
        .amount(amount)
        .result(result)
        .transactionId(getId())
        .dateTime(getCreatedAt())
        .build();
  }

}

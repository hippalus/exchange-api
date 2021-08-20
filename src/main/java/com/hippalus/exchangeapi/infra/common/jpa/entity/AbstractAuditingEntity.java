package com.hippalus.exchangeapi.infra.common.jpa.entity;

import com.hippalus.exchangeapi.domain.conversion.model.Conversion;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column
  private LocalDateTime updatedAt;

  public abstract Conversion toModel();
}
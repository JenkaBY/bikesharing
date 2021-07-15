package com.godeltech.bikesharing.persistence.entity.common;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {
  @CreatedDate
  @Column(name = "created", updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated")
  private LocalDateTime updatedAt;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}

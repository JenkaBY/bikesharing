package com.godeltech.bikesharing.persistence.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {
  @CreatedDate
  @Column(name = "created", updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated")
  private LocalDateTime updatedAt;
}

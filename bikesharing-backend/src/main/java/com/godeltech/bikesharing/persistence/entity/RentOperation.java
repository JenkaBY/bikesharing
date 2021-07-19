package com.godeltech.bikesharing.persistence.entity;

import com.godeltech.bikesharing.persistence.entity.common.AuditableEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "rent_operation")
@EqualsAndHashCode(of = {}, callSuper = false)
public class RentOperation extends AuditableEntity {

  @Column(name = "total_cost")
  private Long totalCost;

  private Long deposit;

  @Column(name = "start_time")
  private LocalDateTime startTime = LocalDateTime.now();

  @Column(name = "end_time")
  private LocalDateTime endTime;

  private String comments;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rent_status_id", nullable = false)
  private RentStatus rentStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_item_id", nullable = false)
  private EquipmentItem equipmentItem;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_account_id", nullable = false)
  private ClientAccount clientAccount;
}

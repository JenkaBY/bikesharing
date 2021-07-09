package com.godeltech.bikesharing.persistence.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@EqualsAndHashCode(of = "id", callSuper = false)
public class RentOperation extends AbstractEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "total_cost")
  private Long totalCost;

  private Long deposit;

  @Column(name = "start_time")
  private LocalDateTime startTime;

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

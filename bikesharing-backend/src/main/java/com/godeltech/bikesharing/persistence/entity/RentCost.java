package com.godeltech.bikesharing.persistence.entity;

import com.godeltech.bikesharing.persistence.entity.common.AuditableEntity;
import com.godeltech.bikesharing.persistence.entity.lookup.EquipmentGroup;
import com.godeltech.bikesharing.persistence.entity.lookup.TimePeriod;
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
@Table(name = "equipment_time_rentcost")
@EqualsAndHashCode(callSuper = true, of = {})
public class RentCost extends AuditableEntity {

  private Long cost;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_group_id", nullable = false)
  private EquipmentGroup equipmentGroup;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "time_period_id", nullable = false)
  private TimePeriod timePeriod;
}

package com.godeltech.bikesharing.persistence.entity;

import com.godeltech.bikesharing.persistence.entity.common.AuditableEntity;
import com.godeltech.bikesharing.persistence.entity.lookup.EquipmentGroup;
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
@Table(name = "required_maintenance_period")
@EqualsAndHashCode(callSuper = true, of = {})
public class RequiredMaintenancePeriod extends AuditableEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_group_id", nullable = false)
  private EquipmentGroup equipmentGroup;

  @Column(name = "interval_in_hours", nullable = false)
  private Long intervalInHours;
}

package com.godeltech.bikesharing.persistence.entity;

import com.godeltech.bikesharing.persistence.entity.common.AuditableEntity;
import java.time.LocalDate;
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
@Table(name = "time_in_use")
@EqualsAndHashCode(callSuper = true, of = {})
public class TimeInUse extends AuditableEntity {

  @Column(name = "minutes_in_use")
  private Long minutesInUse;

  @Column(name = "maintenance_date")
  private LocalDate maintenanceDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_item_id", nullable = false)
  private EquipmentItem equipmentItem;
}

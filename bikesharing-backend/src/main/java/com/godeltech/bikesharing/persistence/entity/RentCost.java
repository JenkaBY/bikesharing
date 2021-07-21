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
@Table(name = "equipment_rentcost")
@EqualsAndHashCode(callSuper = true, of = {})
public class RentCost extends AuditableEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_group_id", nullable = false)
  private EquipmentGroup equipmentGroup;

  @Column(name = "h_half_price", nullable = false)
  private Long halfHourPrice;

  @Column(name = "h_price", nullable = false)
  private Long oneHourPrice;

  @Column(name = "day_price", nullable = false)
  private Long dayPrice;

  @Column(name = "min_price_4_h", nullable = false)
  private Long minimalHourPrice;

  @Column(name = "h_discount", nullable = false)
  private Long hourDiscount;
}

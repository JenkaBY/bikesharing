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
@Table(name = "equipment_item")
@EqualsAndHashCode(callSuper = true, of = {})
public class EquipmentItem extends AuditableEntity {

  private String name;

  @Column(name = "registration_number", nullable = false, unique = true)
  private String registrationNumber;

  @Column(name = "factory_number", nullable = false)
  private String factoryNumber;

  @Column(name = "purchase_date")
  private LocalDate purchaseDate;

  private String comments;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_group_id", nullable = false)
  private EquipmentGroup equipmentGroup;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_status_id", nullable = false)
  private EquipmentStatus equipmentStatus;
}

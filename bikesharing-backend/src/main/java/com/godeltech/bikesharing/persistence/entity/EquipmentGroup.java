package com.godeltech.bikesharing.persistence.entity;

import com.godeltech.bikesharing.persistence.entity.common.LookupEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "equipment_group")
public class EquipmentGroup extends LookupEntity {

}

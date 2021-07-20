package com.godeltech.bikesharing.persistence.entity.lookup;

import com.godeltech.bikesharing.persistence.entity.common.LookupEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rent_status")
public class RentStatus extends LookupEntity {

}

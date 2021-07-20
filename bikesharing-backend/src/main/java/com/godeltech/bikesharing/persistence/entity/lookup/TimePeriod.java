package com.godeltech.bikesharing.persistence.entity.lookup;

import com.godeltech.bikesharing.persistence.entity.common.LookupEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "time_period")
public class TimePeriod extends LookupEntity {

}

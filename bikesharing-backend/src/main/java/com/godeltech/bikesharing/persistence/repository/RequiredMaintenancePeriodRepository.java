package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.RequiredMaintenancePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequiredMaintenancePeriodRepository extends JpaRepository<RequiredMaintenancePeriod, Long> {

}

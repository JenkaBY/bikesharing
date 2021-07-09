package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {
}

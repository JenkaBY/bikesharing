package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.ServiceOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOperationRepository extends JpaRepository<ServiceOperation, Long> {
}

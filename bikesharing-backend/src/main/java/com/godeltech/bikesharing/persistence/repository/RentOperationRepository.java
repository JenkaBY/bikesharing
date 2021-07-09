package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.RentOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentOperationRepository extends JpaRepository<RentOperation, Long> {
}

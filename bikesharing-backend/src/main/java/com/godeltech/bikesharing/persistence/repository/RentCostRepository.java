package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.RentCost;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCostRepository extends JpaRepository<RentCost, Long> {
  Optional<RentCost> findByEquipmentGroupCode(String equipmentGroupCode);

}

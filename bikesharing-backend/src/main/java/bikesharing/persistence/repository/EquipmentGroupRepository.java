package bikesharing.persistence.repository;

import bikesharing.persistence.entity.EquipmentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentGroupRepository extends JpaRepository<EquipmentGroup, Long> {
}

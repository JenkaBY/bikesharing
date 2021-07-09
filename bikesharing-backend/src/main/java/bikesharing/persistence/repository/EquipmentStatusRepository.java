package bikesharing.persistence.repository;

import bikesharing.persistence.entity.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentStatusRepository extends JpaRepository<EquipmentStatus, Long> {
}

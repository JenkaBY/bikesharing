package bikesharing.persistence.repository;

import bikesharing.persistence.entity.EquipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentItemRepository extends JpaRepository<EquipmentItem, Long> {
}

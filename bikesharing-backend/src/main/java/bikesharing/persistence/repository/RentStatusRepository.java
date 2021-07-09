package bikesharing.persistence.repository;

import bikesharing.persistence.entity.RentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentStatusRepository extends JpaRepository<RentStatus, Long> {
}

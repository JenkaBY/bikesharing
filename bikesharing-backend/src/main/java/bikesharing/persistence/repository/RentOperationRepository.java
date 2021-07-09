package bikesharing.persistence.repository;

import bikesharing.persistence.entity.RentOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentOperationRepository extends JpaRepository<RentOperation, Long> {
}

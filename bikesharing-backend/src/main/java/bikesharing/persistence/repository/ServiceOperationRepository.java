package bikesharing.persistence.repository;

import bikesharing.persistence.entity.ServiceOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOperationRepository extends JpaRepository<ServiceOperation, Long> {
}

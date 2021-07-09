package bikesharing.persistence.repository;

import bikesharing.persistence.entity.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAccountRepository extends JpaRepository<ClientAccount, Long> {
}

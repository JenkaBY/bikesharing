package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.common.LookupEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LookupRepository<T extends LookupEntity> extends JpaRepository<T, Long> {
  Optional<T> findByCode(String code);
}

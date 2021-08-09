package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentOperationRepository extends JpaRepository<RentOperation, Long> {

  Optional<RentOperation> getByEquipmentItemRegistrationNumberAndRentStatusCode(String registrationNumber, String code);

  List<RentOperation> findByRentStatusCode(String statusCode);

  @Query(nativeQuery = true)
  List<IncomeDetailsItemModel> getIncomeDetails(@Param("startDate") LocalDate startDate,
                                                @Param("finishDate") LocalDate finishDate);
}

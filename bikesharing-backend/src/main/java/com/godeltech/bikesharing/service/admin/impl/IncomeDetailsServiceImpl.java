package com.godeltech.bikesharing.service.admin.impl;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.persistence.repository.RentOperationRepository;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.util.DateUtils;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncomeDetailsServiceImpl implements IncomeDetailsService {
  private final RentOperationRepository repository;

  @Override
  public List<IncomeDetailsItemModel> getAllIncomeDetailsItems(IncomeTimeUnit timeUnit,
                                                               LocalDate finishDateFromRequest) {
    log.info("Get Income Details Items for timeUnit: {} and finishDate: {} ",
        timeUnit, finishDateFromRequest);
    var finishDate = DateUtils.getFinishDate(finishDateFromRequest);
    var startDate = DateUtils.getStartDate(timeUnit, finishDate);
    return repository.getIncomeDetails(startDate, finishDate);
  }
}
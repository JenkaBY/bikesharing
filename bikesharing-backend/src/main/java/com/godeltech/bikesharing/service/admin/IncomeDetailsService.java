package com.godeltech.bikesharing.service.admin;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import java.time.LocalDate;
import java.util.List;

public interface IncomeDetailsService {

  List<IncomeDetailsItemModel> getAllIncomeDetailsItems(IncomeTimeUnit timeUnit, LocalDate finishDate);

}

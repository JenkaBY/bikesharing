package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.models.response.IncomeDetailsItemResponse;
import org.mapstruct.Mapper;

@Mapper
public interface IncomeDetailsItemMapper {

  IncomeDetailsItemResponse mapToResponse(IncomeDetailsItemModel model);
}

package com.godeltech.bikesharing.service.maintenance;

import com.godeltech.bikesharing.models.RequiredMaintenanceDetailsModel;
import java.util.List;

public interface RequiredMaintenanceDetailsService {

  List<RequiredMaintenanceDetailsModel> getAllRequiredMaintenanceDetailsItems();
}

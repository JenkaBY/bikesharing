package com.godeltech.bikesharing.persistence.entity;

import com.godeltech.bikesharing.models.RequiredMaintenanceDetailsModel;
import com.godeltech.bikesharing.persistence.entity.common.AuditableEntity;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@SqlResultSetMapping(
    name = "requiredMaintenanceDetailsMapping",
    classes = {
        @ConstructorResult(
            targetClass = RequiredMaintenanceDetailsModel.class,
            columns = {
                @ColumnResult(name = "equipmentGroupCode"),
                @ColumnResult(name = "equipmentRegistrationNumber"),
                @ColumnResult(name = "maintenanceDate", type = LocalDate.class),
                @ColumnResult(name = "timeInUse", type = Long.class),
                @ColumnResult(name = "intervalInHours", type = Long.class)
            }
        )
    }
)
@NamedNativeQuery(name = "TimeInUse.getRequiredMaintenanceDetails", query =
    "SELECT t.maintenance_date as maintenanceDate, "
        + "g.code as equipmentGroupCode, "
        + "e.registration_number  as equipmentRegistrationNumber, "
        + "(t.minutes_in_use / 60) as timeInUse, "
        + "m.interval_in_hours  as intervalInHours "
        + "FROM time_in_use t "
        + "LEFT JOIN equipment_item e ON t.equipment_item_id = e.id "
        + "LEFT JOIN equipment_group g ON e.equipment_group_id = g.id "
        + "INNER JOIN required_maintenance_period m ON m.equipment_group_id = g.id "
        + "ORDER BY maintenanceDate",
    resultSetMapping = "requiredMaintenanceDetailsMapping")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "time_in_use")
@EqualsAndHashCode(callSuper = true, of = {})
public class TimeInUse extends AuditableEntity {

  @Column(name = "minutes_in_use")
  private Long minutesInUse;

  @Column(name = "maintenance_date")
  private LocalDate maintenanceDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_item_id", nullable = false)
  private EquipmentItem equipmentItem;
}

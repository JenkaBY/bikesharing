package com.godeltech.bikesharing.persistence.entity;

import com.godeltech.bikesharing.models.IncomeDetailsItemModel;
import com.godeltech.bikesharing.persistence.entity.common.AuditableEntity;
import com.godeltech.bikesharing.persistence.entity.lookup.RentStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    name = "groupDetailsMapping",
    classes = {
        @ConstructorResult(
            targetClass = IncomeDetailsItemModel.class,
            columns = {
                @ColumnResult(name = "date", type = LocalDate.class),
                @ColumnResult(name = "equipmentGroupCode"),
                @ColumnResult(name = "equipmentRegistrationNumber"),
                @ColumnResult(name = "incomeAmount", type = Long.class)
            }
        )
    }
)
@NamedNativeQuery(name = "RentOperation.getIncomeDetails", query =
    "SELECT DATE(r.end_time) as date, "
        + "g.code as equipmentGroupCode, "
        + "e.registration_number  as equipmentRegistrationNumber, "
        + "SUM(r.total_cost) + COALESCE(SUM(r.fines), 0) as incomeAmount "
        + "FROM rent_operation r "
        + "LEFT JOIN equipment_item e ON r.equipment_item_id = e.id "
        + "LEFT JOIN equipment_group g ON e.equipment_group_id = g.id "
        + "LEFT JOIN rent_status s ON r.rent_status_id = s.id "
        + "WHERE DATE(r.end_time) BETWEEN :startDate AND :finishDate "
        + "AND s.code = 'CLOSED' "
        + "GROUP BY equipmentGroupCode, equipmentRegistrationNumber, date "
        + "ORDER BY date",
    resultSetMapping = "groupDetailsMapping")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "rent_operation")
@EqualsAndHashCode(of = {}, callSuper = false)
public class RentOperation extends AuditableEntity {

  @Column(name = "total_cost")
  private Long totalCost;

  private Long deposit;

  @Column(name = "start_time")
  private LocalDateTime startTime = LocalDateTime.now();

  @Column(name = "end_time")
  private LocalDateTime finishedAtTime;

  private Long fines;

  private String comments;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rent_status_id", nullable = false)
  private RentStatus rentStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "equipment_item_id", nullable = false)
  private EquipmentItem equipmentItem;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_account_id", nullable = false)
  private ClientAccount clientAccount;
}

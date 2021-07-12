package com.godeltech.bikesharing.utils;


import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import java.time.LocalDate;


public class EquipmentItemUtils {
  public static final String REGISTRATION_NUMBER = "Number0001";
  public static final String FACTORY_NUMBER = "Number0002";
  public static final LocalDate PURCHASE_DATE = LocalDate.of(2021, 1, 1);

  public static EquipmentItem getEquipmentItem() {
    var equipmentItem = new EquipmentItem();
    equipmentItem.setRegistrationNumber(REGISTRATION_NUMBER);
    equipmentItem.setFactoryNumber(FACTORY_NUMBER);
    equipmentItem.setPurchaseDate(PURCHASE_DATE);
    equipmentItem.setEquipmentGroup(EquipmentGroupUtils.getEquipmentGroup());
    equipmentItem.setEquipmentStatus(EquipmentStatusUtils.getEquipmentStatus());
    return equipmentItem;
  }

  public static EquipmentItemModel getEquipmentItemModel(Long id) {
    var equipmentItemModel = new EquipmentItemModel();
    equipmentItemModel.setId(id);
    equipmentItemModel.setRegistrationNumber(REGISTRATION_NUMBER);
    equipmentItemModel.setFactoryNumber(FACTORY_NUMBER);
    equipmentItemModel.setPurchaseDate(PURCHASE_DATE);
    equipmentItemModel.setEquipmentGroup(EquipmentGroupUtils.getEquipmentGroupModel());
    equipmentItemModel.setEquipmentStatus(EquipmentStatusUtils.getEquipmentStatusModel());
    return equipmentItemModel;
  }
}

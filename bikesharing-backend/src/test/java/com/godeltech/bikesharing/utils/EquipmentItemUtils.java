package com.godeltech.bikesharing.utils;


import com.godeltech.bikesharing.models.EquipmentItemModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.models.request.EquipmentItemRequest;
import com.godeltech.bikesharing.models.response.EquipmentItemResponse;
import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import java.time.LocalDate;


public class EquipmentItemUtils {

  public static final String REGISTRATION_NUMBER = "equipmentNum1";
  public static final String FACTORY_NUMBER = "factoryNum1";
  public static final String NAME = "Aist Classic";
  public static final String COMMENTS = "simple road bike, single speed, 29' wheel";
  public static final LocalDate PURCHASE_DATE = LocalDate.of(2021, 1, 23);

  public static EquipmentItem getEquipmentItem() {
    var equipmentItem = new EquipmentItem();
    equipmentItem.setRegistrationNumber(REGISTRATION_NUMBER);
    equipmentItem.setFactoryNumber(FACTORY_NUMBER);
    equipmentItem.setName(NAME);
    equipmentItem.setPurchaseDate(PURCHASE_DATE);
    equipmentItem.setComments(COMMENTS);
    equipmentItem.setEquipmentGroup(EquipmentGroupUtils.getEquipmentGroup());
    equipmentItem.setEquipmentStatus(EquipmentStatusUtils.getEquipmentStatusFree());
    return equipmentItem;
  }

  public static EquipmentItemModel getEquipmentItemModel(Long id) {
    var equipmentItemModel = new EquipmentItemModel();
    equipmentItemModel.setId(id);
    equipmentItemModel.setRegistrationNumber(REGISTRATION_NUMBER);
    equipmentItemModel.setFactoryNumber(FACTORY_NUMBER);
    equipmentItemModel.setName(NAME);
    equipmentItemModel.setPurchaseDate(PURCHASE_DATE);
    equipmentItemModel.setComments(COMMENTS);
    equipmentItemModel.setEquipmentGroup(EquipmentGroupUtils.getEquipmentGroupModel());
    equipmentItemModel.setEquipmentStatus(EquipmentStatusUtils.getEquipmentStatusFreeModel());
    return equipmentItemModel;
  }

  public static EquipmentItemRequest getEquipmentItemRequest() {
    var equipmentItem = new EquipmentItemRequest();
    equipmentItem.setRegistrationNumber(REGISTRATION_NUMBER);
    equipmentItem.setFactoryNumber(FACTORY_NUMBER);
    equipmentItem.setName(NAME);
    equipmentItem.setPurchaseDate(PURCHASE_DATE);
    equipmentItem.setComment(COMMENTS);
    equipmentItem.setEquipmentGroupCode(EquipmentGroupUtils.CODE);
    equipmentItem.setEquipmentStatusCode(EquipmentStatusModel.EQUIPMENT_ITEM_STATUS_FREE);
    return equipmentItem;
  }

  public static EquipmentItemResponse getEquipmentItemResponse(Long id) {
    var equipmentItem = new EquipmentItemResponse();
    equipmentItem.setId(id);
    equipmentItem.setRegistrationNumber(REGISTRATION_NUMBER);
    equipmentItem.setFactoryNumber(FACTORY_NUMBER);
    equipmentItem.setName(NAME);
    equipmentItem.setPurchaseDate(PURCHASE_DATE);
    equipmentItem.setComments(COMMENTS);
    equipmentItem.setEquipmentGroup(EquipmentGroupUtils.getEquipmentGroupResponse(id));
    equipmentItem.setEquipmentStatus(EquipmentStatusUtils.getEquipmentStatusResponse(id));
    return equipmentItem;
  }
}

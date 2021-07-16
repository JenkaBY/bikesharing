package com.godeltech.bikesharing.persistence.entity;

import com.godeltech.bikesharing.persistence.entity.common.AuditableEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "client_account")
@EqualsAndHashCode(callSuper = false, of = {})
public class ClientAccount extends AuditableEntity {

  private String name;

  private String address;

  @Column(name = "phone_number", nullable = false, unique = true)
  private String phoneNumber;

  private int rating;

  private String comments;

  public ClientAccount(String clientPhoneNumber) {
    super();
    this.phoneNumber = clientPhoneNumber;
  }
}

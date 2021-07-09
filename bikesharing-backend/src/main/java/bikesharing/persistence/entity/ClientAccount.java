package bikesharing.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@EqualsAndHashCode(of = "id", callSuper = false)
public class ClientAccount extends AbstractEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String address;

  @Column(name = "phone_number", nullable = false, unique = true)
  private String phoneNumber;

  private Integer rating;

  private String comments;
}

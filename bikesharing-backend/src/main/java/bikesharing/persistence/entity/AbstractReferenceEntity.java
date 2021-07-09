package bikesharing.persistence.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@RequiredArgsConstructor
@EqualsAndHashCode(of = "code")
public abstract class AbstractReferenceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 255)
  private String name;

  @Size(max = 50)
  @Column(name = "code", nullable = false, unique = true)
  private String code;
}

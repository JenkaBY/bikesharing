package bikesharing.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "service_type")
public class ServiceType extends AbstractReferenceEntity {

}

package deni.osmani.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="table_service_type")
public class ServiceTypeEntity implements deni.osmani.entity.Entity{
	@Id
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceTypeEntity other = (ServiceTypeEntity) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "ServiceTypeEntity [value=" + value + "]";
	}

	public ServiceTypeEntity(String value) {
		super();
		this.value = value;
	}
	public ServiceTypeEntity() {
	}
}

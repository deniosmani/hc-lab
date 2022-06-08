package deni.osmani.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="table_marital_status")
public class MartialStatusEntity {
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
		MartialStatusEntity other = (MartialStatusEntity) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "MartialStatusEntity [value=" + value + "]";
	}

	public MartialStatusEntity(String value) {
		super();
		this.value = value;
	}
	public MartialStatusEntity() {
	}
}

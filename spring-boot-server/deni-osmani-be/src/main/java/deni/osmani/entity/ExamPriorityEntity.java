package deni.osmani.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="table_exam_priority")
public class ExamPriorityEntity {
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
		ExamPriorityEntity other = (ExamPriorityEntity) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "ExamPriorityEntity [value=" + value + "]";
	}

	public ExamPriorityEntity(String value) {
		super();
		this.value = value;
	}
	public ExamPriorityEntity() {
	}
}

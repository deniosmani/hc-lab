package deni.osmani.dto;

import java.util.Objects;

public class QualificationDto {
	private String value;

	public QualificationDto(String value) {
		super();
		this.value = value;
	}

	@Override
	public String toString() {
		return "QualificationDto [value=" + value + "]";
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
		QualificationDto other = (QualificationDto) obj;
		return Objects.equals(value, other.value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public QualificationDto() {
	}
}

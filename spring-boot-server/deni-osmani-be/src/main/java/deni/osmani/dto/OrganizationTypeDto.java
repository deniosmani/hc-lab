package deni.osmani.dto;

import java.util.Objects;

public class OrganizationTypeDto {
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
		OrganizationTypeDto other = (OrganizationTypeDto) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "OrganizationTypeDto [value=" + value + "]";
	}

	public OrganizationTypeDto(String value) {
		super();
		this.value = value;
	}
	public OrganizationTypeDto() {
	}
}

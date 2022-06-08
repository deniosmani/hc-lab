package deni.osmani.dto;

import java.util.Objects;


public class ServiceTypeDto {
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
		ServiceTypeDto other = (ServiceTypeDto) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "ServiceTypeDto [value=" + value + "]";
	}

	public ServiceTypeDto(String value) {
		super();
		this.value = value;
	}
	public ServiceTypeDto() {
	}
}

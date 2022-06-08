package deni.osmani.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationDto implements deni.osmani.dto.Dto{
	private Long id;
	@NotNull
	private OrganizationTypeDto organizationType;
	@NotBlank
	@Size(min=5)
	private String name;
	private String address;
	private String phone;
	@Email
	private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OrganizationTypeDto getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(OrganizationTypeDto organizationType) {
		this.organizationType = organizationType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, email, id, name, organizationType, phone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationDto other = (OrganizationDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(organizationType, other.organizationType) && Objects.equals(phone, other.phone);
	}
	@Override
	public String toString() {
		return "OrganizationDto [id=" + id + ", organizationType=" + organizationType + ", name=" + name + ", address="
				+ address + ", phone=" + phone + ", email=" + email + "]";
	}
	public OrganizationDto(Long id, OrganizationTypeDto organizationType, String name, String address, String phone,
			String email) {
		super();
		this.id = id;
		this.organizationType = organizationType;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	public OrganizationDto() {
	}
	
}

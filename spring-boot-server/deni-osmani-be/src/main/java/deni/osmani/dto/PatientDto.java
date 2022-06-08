package deni.osmani.dto;

import java.sql.Date;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PatientDto implements deni.osmani.dto.Dto{
	private Long id;
	@NotBlank
	@Size(min=3)
	private String name;
	@NotBlank
	@Size(min=3)
	private String surname;
	@NotNull
	private Date birthDate;
	private String address;
	private String phone;
	@Email
	private String email;
	private boolean deceased;
	private OrganizationDto organization;
	private PractitionerDto primaryCareProvider;
	private GenderDto gender;
	private MartialStatusDto martialStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	public boolean isDeceased() {
		return deceased;
	}
	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}
	public OrganizationDto getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
	public PractitionerDto getPrimaryCareProvider() {
		return primaryCareProvider;
	}
	public void setPrimaryCareProvider(PractitionerDto primaryCareProvider) {
		this.primaryCareProvider = primaryCareProvider;
	}
	public GenderDto getGender() {
		return gender;
	}
	public void setGender(GenderDto gender) {
		this.gender = gender;
	}
	public MartialStatusDto getMartialStatus() {
		return martialStatus;
	}
	public void setMartialStatus(MartialStatusDto martialStatus) {
		this.martialStatus = martialStatus;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, birthDate, deceased, email, gender, id, martialStatus, name, organization, phone,
				primaryCareProvider, surname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientDto other = (PatientDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
				&& deceased == other.deceased && Objects.equals(email, other.email)
				&& Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
				&& Objects.equals(martialStatus, other.martialStatus) && Objects.equals(name, other.name)
				&& Objects.equals(organization, other.organization) && Objects.equals(phone, other.phone)
				&& Objects.equals(primaryCareProvider, other.primaryCareProvider)
				&& Objects.equals(surname, other.surname);
	}
	@Override
	public String toString() {
		return "PatientDto [id=" + id + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate
				+ ", address=" + address + ", phone=" + phone + ", email=" + email + ", deceased=" + deceased
				+ ", organization=" + organization + ", primaryCareProvider=" + primaryCareProvider + ", gender="
				+ gender + ", martialStatus=" + martialStatus + "]";
	}
	public PatientDto(Long id, String name, String surname, Date birthDate, String address, String phone, String email,
			boolean deceased, OrganizationDto organization, PractitionerDto primaryCareProvider, GenderDto gender,
			MartialStatusDto martialStatus) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.deceased = deceased;
		this.organization = organization;
		this.primaryCareProvider = primaryCareProvider;
		this.gender = gender;
		this.martialStatus = martialStatus;
	}
	public PatientDto() {
	}
	
}

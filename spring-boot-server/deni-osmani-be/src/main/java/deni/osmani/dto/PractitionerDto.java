package deni.osmani.dto;

import java.sql.Date;
import java.util.Objects;

public class PractitionerDto implements deni.osmani.dto.Dto{
	private Long id;
	private String name;
	private String surname;
	private GenderDto gender;
	private Date birthDay;
	private String address;
	private String phone;
	private String email;
	private QualificationDto qualification;
	private OrganizationDto organization;
	
	public PractitionerDto(Long id, String name, String surname, GenderDto gender, Date birthDay, String address,
			String phone, String email, QualificationDto qualification, OrganizationDto organization) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthDay = birthDay;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.qualification = qualification;
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "PractitionerDto [id=" + id + ", name=" + name + ", surname=" + surname + ", gender=" + gender
				+ ", birthDay=" + birthDay + ", address=" + address + ", phone=" + phone + ", email=" + email
				+ ", qualification=" + qualification + ", organization=" + organization + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, birthDay, email, gender, id, name, organization, phone, qualification, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PractitionerDto other = (PractitionerDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(birthDay, other.birthDay)
				&& Objects.equals(email, other.email) && Objects.equals(gender, other.gender)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(organization, other.organization) && Objects.equals(phone, other.phone)
				&& Objects.equals(qualification, other.qualification) && Objects.equals(surname, other.surname);
	}

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

	public GenderDto getGender() {
		return gender;
	}

	public void setGender(GenderDto gender) {
		this.gender = gender;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
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

	public QualificationDto getQualification() {
		return qualification;
	}

	public void setQualification(QualificationDto qualification) {
		this.qualification = qualification;
	}

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}

	public PractitionerDto() {
	}
}

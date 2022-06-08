package deni.osmani.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="table_practitioner")
@SQLDelete(sql="UPDATE table_practitioner SET active=false WHERE id=?")
@Where(clause = "active=true")
public class PractitionerEntity implements deni.osmani.entity.Entity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean active=Boolean.TRUE;
	private String name;
	private String surname;
	private Date birthDate;
	private String address;
	private String phone;
	private String email;
	@ManyToOne
	private GenderEntity gender;
	@ManyToOne
	private QualificationEntity qualification;	
	@ManyToOne
	private OrganizationEntity organization;
	/*@ManyToMany
	private List<ExaminationEntity> examinations;*/
	
	@Override
	public int hashCode() {
		return Objects.hash(active, address, birthDate, email, gender, id, name, organization, phone,
				qualification, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PractitionerEntity other = (PractitionerEntity) obj;
		return Objects.equals(active, other.active) && Objects.equals(address, other.address)
				&& Objects.equals(birthDate, other.birthDate) && Objects.equals(email, other.email)
				&& Objects.equals(gender, other.gender)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(organization, other.organization) && Objects.equals(phone, other.phone)
				&& Objects.equals(qualification, other.qualification) && Objects.equals(surname, other.surname);
	}

	public PractitionerEntity(Long id, Boolean active, String name, String surname, Date birthDate, String address,
			String phone, String email, GenderEntity gender, QualificationEntity qualification,
			OrganizationEntity organization) {
		super();
		this.id = id;
		this.active = active;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.qualification = qualification;
		this.organization = organization;
	
	}

	@Override
	public String toString() {
		return "PractitionerEntity [id=" + id + ", active=" + active + ", name=" + name + ", surname=" + surname
				+ ", birthDate=" + birthDate + ", address=" + address + ", phone=" + phone + ", email=" + email
				+ ", gender=" + gender + ", qualification=" + qualification + ", organization=" + organization
				 + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	public GenderEntity getGender() {
		return gender;
	}

	public void setGender(GenderEntity gender) {
		this.gender = gender;
	}

	public QualificationEntity getQualification() {
		return qualification;
	}

	public void setQualification(QualificationEntity qualification) {
		this.qualification = qualification;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}



	public PractitionerEntity() {
	}
}

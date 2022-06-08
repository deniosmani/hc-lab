package deni.osmani.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="table_organization")
@SQLDelete(sql="UPDATE table_organization SET active=false WHERE id=?")
@Where(clause = "active=true")
public class OrganizationEntity implements deni.osmani.entity.Entity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean active=Boolean.TRUE;
	@ManyToOne
	@JoinColumn(name="type")
	private OrganizationTypeEntity type;
	@Column(unique = true)
	private String name;
	private String address;
	private String phone;
	private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public OrganizationTypeEntity getType() {
		return type;
	}
	public void setType(OrganizationTypeEntity type) {
		this.type = type;
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
		return Objects.hash(active, address, email, id, name, phone, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizationEntity other = (OrganizationEntity) obj;
		return active == other.active && Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone) && Objects.equals(type, other.type);
	}
	public OrganizationEntity(Long id, boolean active, OrganizationTypeEntity type, String name, String address,
			String phone, String email) {
		this.id = id;
		this.active = active;
		this.type = type;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	@Override
	public String toString() {
		return "OrganizationEntity [id=" + id + ", active=" + active + ", type=" + type + ", name=" + name
				+ ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
	}
	
	public OrganizationEntity() {
	}
}

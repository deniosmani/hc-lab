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
@Table(name="table_examination")
@SQLDelete(sql="UPDATE table_examination SET status_value='entered-in-error' WHERE id=?")
@Where(clause = "status_value<>'entered-in-error'")
public class ExaminationEntity implements deni.osmani.entity.Entity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="serviceType")
	private ServiceTypeEntity serviceType;
	private Date startDate;
	private Date endDate;
	private String diagnosis;
	@ManyToOne
	@JoinColumn(name="patient")
	private PatientEntity patient;
	@ManyToMany
	private List<PractitionerEntity> practitioners;
	@ManyToOne(optional = false)
	@JoinColumn(name="organization")
	private OrganizationEntity organization;
	@ManyToOne
	private ExamPriorityEntity priority;
	@ManyToOne
	private ExamStatusEntity status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ServiceTypeEntity getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceTypeEntity serviceType) {
		this.serviceType = serviceType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public PatientEntity getPatient() {
		return patient;
	}
	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
	public List<PractitionerEntity> getPractitioners() {
		return practitioners;
	}
	public void setPractitioners(List<PractitionerEntity> practitioners) {
		this.practitioners = practitioners;
	}
	public OrganizationEntity getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}
	public ExamPriorityEntity getPriority() {
		return priority;
	}
	public void setPriority(ExamPriorityEntity priority) {
		this.priority = priority;
	}
	public ExamStatusEntity getStatus() {
		return status;
	}
	public void setStatus(ExamStatusEntity status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return Objects.hash(diagnosis, endDate, id, organization, patient, practitioners, priority, serviceType,
				startDate, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExaminationEntity other = (ExaminationEntity) obj;
		return Objects.equals(diagnosis, other.diagnosis) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(id, other.id) && Objects.equals(organization, other.organization)
				&& Objects.equals(patient, other.patient) && Objects.equals(practitioners, other.practitioners)
				&& Objects.equals(priority, other.priority) && Objects.equals(serviceType, other.serviceType)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(status, other.status);
	}
	@Override
	public String toString() {
		return "ExaminationEntity [id=" + id + ", serviceType=" + serviceType + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", diagnosis=" + diagnosis + ", patient=" + patient + ", practitioners="
				+ practitioners + ", organization=" + organization + ", priority=" + priority + ", status=" + status
				+ "]";
	}
	public ExaminationEntity(Long id, ServiceTypeEntity serviceType, Date startDate, Date endDate, String diagnosis,
			PatientEntity patient, List<PractitionerEntity> practitioners, OrganizationEntity organization,
			ExamPriorityEntity priority, ExamStatusEntity status) {
		super();
		this.id = id;
		this.serviceType = serviceType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.diagnosis = diagnosis;
		this.patient = patient;
		this.practitioners = practitioners;
		this.organization = organization;
		this.priority = priority;
		this.status = status;
	}
	public ExaminationEntity() {
	}
}

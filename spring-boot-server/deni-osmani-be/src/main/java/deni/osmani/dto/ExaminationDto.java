package deni.osmani.dto;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class ExaminationDto implements deni.osmani.dto.Dto{
	private Long id;
	@NotNull
	private ServiceTypeDto serviceType;
	private Date startDate;
	private Date endDate;
	private String diagnosis;
	@NotNull
	private PatientDto patient;
	private List<PractitionerDto> practitioners;
	@NotNull
	private OrganizationDto organization;
	private ExamPriorityDto priority;
	private ExamStatusDto status;
	public Long getId() {
		return id;
	}
	public ExaminationDto() {
	}
	public ExaminationDto(Long id, ServiceTypeDto serviceTypeDto, Date startDate, Date endDate, String diagnosis,
			PatientDto patient, List<PractitionerDto> practitioners, OrganizationDto organization,
			ExamPriorityDto priority, ExamStatusDto status) {
		super();
		this.id = id;
		this.serviceType = serviceTypeDto;
		this.startDate = startDate;
		this.endDate = endDate;
		this.diagnosis = diagnosis;
		this.patient = patient;
		this.practitioners = practitioners;
		this.organization = organization;
		this.priority = priority;
		this.status = status;
	}
	@Override
	public String toString() {
		return "ExaminationDto [id=" + id + ", serviceTypeDto=" + serviceType + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", diagnosis=" + diagnosis + ", patient=" + patient + ", practitioners="
				+ practitioners + ", organization=" + organization + ", priority=" + priority + ", status=" + status
				+ "]";
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
		ExaminationDto other = (ExaminationDto) obj;
		return Objects.equals(diagnosis, other.diagnosis) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(id, other.id) && Objects.equals(organization, other.organization)
				&& Objects.equals(patient, other.patient) && Objects.equals(practitioners, other.practitioners)
				&& Objects.equals(priority, other.priority) && Objects.equals(serviceType, other.serviceType)
				&& Objects.equals(startDate, other.startDate) && Objects.equals(status, other.status);
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ServiceTypeDto getServiceTypeDto() {
		return serviceType;
	}
	public void setServiceTypeDto(ServiceTypeDto serviceTypeDto) {
		this.serviceType = serviceTypeDto;
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
	public PatientDto getPatient() {
		return patient;
	}
	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}
	public List<PractitionerDto> getPractitioners() {
		return practitioners;
	}
	public void setPractitioners(List<PractitionerDto> practitioners) {
		this.practitioners = practitioners;
	}
	public OrganizationDto getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
	public ExamPriorityDto getPriority() {
		return priority;
	}
	public void setPriority(ExamPriorityDto priority) {
		this.priority = priority;
	}
	public ExamStatusDto getStatus() {
		return status;
	}
	public void setStatus(ExamStatusDto status) {
		this.status = status;
	}
}

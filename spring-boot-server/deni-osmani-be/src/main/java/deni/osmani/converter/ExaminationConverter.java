package deni.osmani.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import deni.osmani.dto.ExamPriorityDto;
import deni.osmani.dto.ExamStatusDto;
import deni.osmani.dto.ExaminationDto;
import deni.osmani.dto.OrganizationDto;
import deni.osmani.dto.PatientDto;
import deni.osmani.dto.PractitionerDto;
import deni.osmani.dto.ServiceTypeDto;
import deni.osmani.entity.ExamPriorityEntity;
import deni.osmani.entity.ExamStatusEntity;
import deni.osmani.entity.ExaminationEntity;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.PatientEntity;
import deni.osmani.entity.PractitionerEntity;
import deni.osmani.entity.ServiceTypeEntity;

@Component
public class ExaminationConverter implements GenericConverter<ExaminationDto, ExaminationEntity> {

	@Autowired
	private OrganizationConverter organizationConverter;
	@Autowired
	private PractitionerConverter practitionerConverter;
	@Autowired
	private PatientConverter patientConverter;

	@Override
	public ExaminationEntity toEntity(ExaminationDto dto) {
		ExaminationEntity entity = new ExaminationEntity();
		OrganizationEntity organizationEntity = null;
		PatientEntity patientEntity = null;
		List<PractitionerEntity> practitionerEntities = new ArrayList<>();
		ServiceTypeEntity serviceTypeEntity = new ServiceTypeEntity();
		ExamStatusEntity examStatusEntity = new ExamStatusEntity();
		ExamPriorityEntity examPriorityEntity = new ExamPriorityEntity();
		if (dto.getServiceTypeDto() != null)
			serviceTypeEntity.setValue(dto.getServiceTypeDto().getValue());
		if (serviceTypeEntity.getValue() != null)
			entity.setServiceType(serviceTypeEntity);
		if (dto.getStatus() != null)
			examStatusEntity.setValue(dto.getStatus().getValue());
		if (examStatusEntity.getValue() != null)
			entity.setStatus(examStatusEntity);
		if (dto.getPriority() != null)
			examPriorityEntity.setValue(dto.getPriority().getValue());
		if (examPriorityEntity.getValue() != null)
			entity.setPriority(examPriorityEntity);
		if (dto.getOrganization() != null)
			organizationEntity = organizationConverter.toEntity(dto.getOrganization());
		if (organizationEntity != null)
			entity.setOrganization(organizationEntity);
		if (dto.getPatient() != null)
			patientEntity = patientConverter.toEntity(dto.getPatient());
		if (patientEntity != null)
			entity.setPatient(patientEntity);
		if (dto.getPractitioners() != null)
			if (!dto.getPractitioners().isEmpty()) {
				practitionerEntities = dto.getPractitioners().stream().map(element -> {
					return practitionerConverter.toEntity(element);
				}).collect(Collectors.toList());
			}

		entity.setPractitioners(practitionerEntities);
		entity.setDiagnosis(dto.getDiagnosis());
		entity.setEndDate(dto.getEndDate());
		entity.setId(dto.getId());
		entity.setStartDate(dto.getStartDate());

		return entity;
	}

	@Override
	public ExaminationDto toDto(ExaminationEntity entity) {
		ExaminationDto dto = new ExaminationDto();
		OrganizationDto organizationDto = null;
		PatientDto patientDto = null;
		List<PractitionerDto> practitionerDtos = new ArrayList<>();
		ServiceTypeDto serviceTypeDto = new ServiceTypeDto();
		ExamStatusDto examStatusDto = new ExamStatusDto();
		ExamPriorityDto examPriorityDto = new ExamPriorityDto();
		if (entity.getServiceType() != null)
			serviceTypeDto.setValue(entity.getServiceType().getValue());
		if (serviceTypeDto.getValue() != null)
			dto.setServiceTypeDto(serviceTypeDto);
		if (entity.getStatus() != null)
			examStatusDto.setValue(entity.getStatus().getValue());
		if (examStatusDto.getValue() != null)
			dto.setStatus(examStatusDto);
		if (entity.getPriority() != null)
			examPriorityDto.setValue(entity.getPriority().getValue());
		if (examPriorityDto.getValue() != null)
			dto.setPriority(examPriorityDto);
		if (entity.getOrganization() != null)
			organizationDto = organizationConverter.toDto(entity.getOrganization());
		if (organizationDto != null)
			dto.setOrganization(organizationDto);
		if (entity.getPatient() != null)
			patientDto = patientConverter.toDto(entity.getPatient());
		if (patientDto != null)
			dto.setPatient(patientDto);
		if (entity.getPractitioners() != null)
			if (!entity.getPractitioners().isEmpty()) {
				practitionerDtos = entity.getPractitioners().stream().map(element -> {
					return practitionerConverter.toDto(element);
				}).collect(Collectors.toList());
			}

		dto.setPractitioners(practitionerDtos);
		dto.setDiagnosis(entity.getDiagnosis());
		dto.setEndDate(entity.getEndDate());
		dto.setId(entity.getId());
		dto.setStartDate(entity.getStartDate());
		return dto;
	}

}

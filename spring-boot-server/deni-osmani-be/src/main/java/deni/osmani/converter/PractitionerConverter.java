package deni.osmani.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import deni.osmani.dto.GenderDto;
import deni.osmani.dto.OrganizationDto;
import deni.osmani.dto.OrganizationTypeDto;
import deni.osmani.dto.PractitionerDto;
import deni.osmani.dto.QualificationDto;
import deni.osmani.entity.GenderEntity;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.OrganizationTypeEntity;
import deni.osmani.entity.PractitionerEntity;
import deni.osmani.entity.QualificationEntity;

@Component
public class PractitionerConverter implements GenericConverter<PractitionerDto, PractitionerEntity> {

	@Autowired
	private OrganizationConverter organizationConverter;

	@Override
	public PractitionerEntity toEntity(PractitionerDto dto) {
		PractitionerEntity entity = new PractitionerEntity();
		OrganizationEntity organizationEntity = null;
		GenderEntity type = new GenderEntity();
		if (dto.getGender() != null)
			type.setValue(dto.getGender().getValue());
		if (type.getValue() != null)
			entity.setGender(type);
		if (dto.getOrganization() != null)
			organizationEntity = organizationConverter.toEntity(dto.getOrganization());
		QualificationEntity qualification = new QualificationEntity();
		if (dto.getQualification() != null)
			qualification.setValue(dto.getQualification().getValue());
		if (qualification.getValue() != null)
			entity.setQualification(qualification);
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		entity.setBirthDate(dto.getBirthDay());
		entity.setEmail(dto.getEmail());
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setSurname(dto.getSurname());
		if (organizationEntity != null)
			entity.setOrganization(organizationEntity);
		return entity;
	}

	@Override
	public PractitionerDto toDto(PractitionerEntity entity) {
		PractitionerDto dto = new PractitionerDto();
		OrganizationDto organizationDto = null;
		if (entity.getOrganization() != null)
			organizationDto = organizationConverter.toDto(entity.getOrganization());
		dto.setAddress(entity.getAddress());
		dto.setPhone(entity.getPhone());
		dto.setBirthDay(entity.getBirthDate());
		dto.setEmail(entity.getEmail());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSurname(entity.getSurname());
		if (organizationDto != null)
			dto.setOrganization(organizationDto);
		GenderDto type = new GenderDto();
		if (entity.getGender() != null)
			type.setValue(entity.getGender().getValue());
		if (type.getValue() != null)
			dto.setGender(type);
		QualificationDto qualification = new QualificationDto();
		if (entity.getQualification() != null)
			qualification.setValue(entity.getQualification().getValue());
		if (qualification.getValue() != null)
			dto.setQualification(qualification);
		return dto;
	}

}

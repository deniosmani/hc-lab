package deni.osmani.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import deni.osmani.dto.GenderDto;
import deni.osmani.dto.MartialStatusDto;
import deni.osmani.dto.OrganizationDto;
import deni.osmani.dto.PatientDto;
import deni.osmani.dto.PractitionerDto;
import deni.osmani.entity.GenderEntity;
import deni.osmani.entity.MartialStatusEntity;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.PatientEntity;
import deni.osmani.entity.PractitionerEntity;

@Component
public class PatientConverter implements GenericConverter<PatientDto, PatientEntity>{

	@Autowired
	private OrganizationConverter organizationConverter;
	@Autowired
	private PractitionerConverter practitionerConverter;
	
	
	@Override
	public PatientEntity toEntity(PatientDto dto) {
		PatientEntity entity = new PatientEntity();
		OrganizationEntity organizationEntity = null;
		PractitionerEntity practitionerEntity = null;
		GenderEntity genderEntity = new GenderEntity();
		if(dto.getGender()!=null)
			genderEntity.setValue(dto.getGender().getValue());
		if(genderEntity.getValue()!=null)
			entity.setGender(genderEntity);
		MartialStatusEntity martialStatusEntity = new MartialStatusEntity();
		if(dto.getMartialStatus()!=null)
			martialStatusEntity.setValue(dto.getMartialStatus().getValue());
		if(martialStatusEntity.getValue()!=null)
			entity.setMartialStatus(martialStatusEntity);
		if(dto.getOrganization()!=null)
			organizationEntity=organizationConverter.toEntity(dto.getOrganization());
		if(dto.getPrimaryCareProvider()!=null)
			practitionerEntity=practitionerConverter.toEntity(dto.getPrimaryCareProvider());
		entity.setAddress(dto.getAddress());
		entity.setBirthDate(dto.getBirthDate());
		entity.setDeceased(dto.isDeceased());
		entity.setEmail(dto.getEmail());
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPhone(dto.getPhone());
		entity.setSurname(dto.getSurname());
		if (organizationEntity != null)
			entity.setOrganization(organizationEntity);
		if (practitionerEntity != null)
			entity.setPrimaryCareProvider(practitionerEntity);
		return entity;
	}

	@Override
	public PatientDto toDto(PatientEntity entity) {
		PatientDto dto = new PatientDto();
		OrganizationDto organizationDto = null;
		PractitionerDto practitionerDto = null;
		GenderDto genderDto = new GenderDto();
		if(entity.getGender()!=null)
			genderDto.setValue(entity.getGender().getValue());
		if(genderDto.getValue()!=null)
			dto.setGender(genderDto);
		MartialStatusDto martialStatusDto = new MartialStatusDto();
		if(entity.getMartialStatus()!=null)
			martialStatusDto.setValue(entity.getMartialStatus().getValue());
		if(martialStatusDto.getValue()!=null)
			dto.setMartialStatus(martialStatusDto);
		if(entity.getOrganization()!=null)
			organizationDto=organizationConverter.toDto(entity.getOrganization());
		if(entity.getPrimaryCareProvider()!=null)
			practitionerDto=practitionerConverter.toDto(entity.getPrimaryCareProvider());
		dto.setAddress(entity.getAddress());
		dto.setBirthDate(entity.getBirthDate());
		dto.setDeceased(entity.isDeceased());
		dto.setEmail(entity.getEmail());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPhone(entity.getPhone());
		dto.setSurname(entity.getSurname());
		if (organizationDto != null)
			dto.setOrganization(organizationDto);
		if (practitionerDto != null)
			dto.setPrimaryCareProvider(practitionerDto);
		return dto;
	}

}

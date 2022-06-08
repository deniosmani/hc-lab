package deni.osmani.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import deni.osmani.dao.OrganizationTypeDao;
import deni.osmani.dto.OrganizationDto;
import deni.osmani.dto.OrganizationTypeDto;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.OrganizationTypeEntity;

@Component
public class OrganizationConverter implements GenericConverter<OrganizationDto, OrganizationEntity> {

	@Autowired
	private OrganizationTypeDao organizationTypeDao;

	@Override
	public OrganizationEntity toEntity(OrganizationDto dto) {
		OrganizationEntity entity = new OrganizationEntity();
		OrganizationTypeEntity type = new OrganizationTypeEntity();
		if (dto.getOrganizationType() != null)
			type.setValue(dto.getOrganizationType().getValue());
		if (type.getValue() != null)
			entity.setType(type);
		entity.setAddress(dto.getAddress());
		entity.setEmail(dto.getEmail());
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPhone(dto.getPhone());

		return entity;
	}

	@Override
	public OrganizationDto toDto(OrganizationEntity entity) {
		OrganizationDto dto = new OrganizationDto();
		OrganizationTypeDto type = new OrganizationTypeDto();
		if (entity.getType() != null)
			type.setValue(entity.getType().getValue());
		if (type.getValue() != null)
			dto.setOrganizationType(type);
		dto.setAddress(entity.getAddress());
		dto.setEmail(entity.getEmail());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPhone(entity.getPhone());
		return dto;
	}

}

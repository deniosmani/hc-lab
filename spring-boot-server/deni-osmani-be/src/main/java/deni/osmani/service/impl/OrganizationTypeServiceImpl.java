package deni.osmani.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deni.osmani.dao.OrganizationTypeDao;
import deni.osmani.dto.OrganizationTypeDto;
import deni.osmani.entity.OrganizationTypeEntity;
import deni.osmani.service.OrganizationTypeService;


@Service
public class OrganizationTypeServiceImpl implements OrganizationTypeService{

	@Autowired
	private OrganizationTypeDao organizationTypeDao;
	
	@Autowired
	
	
	@Override
	public List<OrganizationTypeDto> findAll() {
		List<OrganizationTypeEntity> entities = organizationTypeDao.findAll();
		return entities.stream().map(entity -> {
			return new OrganizationTypeDto(entity.getValue());
		}).collect(Collectors.toList());
	}

}

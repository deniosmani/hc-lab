package deni.osmani.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deni.osmani.dao.ServiceTypeDao;
import deni.osmani.dto.ServiceTypeDto;
import deni.osmani.entity.ServiceTypeEntity;
import deni.osmani.service.ServiceTypeService;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService{

	@Autowired
	private ServiceTypeDao serviceTypeDao;
	
	@Override
	public List<ServiceTypeDto> findAll() {
		List<ServiceTypeEntity> entities = serviceTypeDao.findAll();
		return entities.stream().map(entity -> {
			return new ServiceTypeDto(entity.getValue());
		}).collect(Collectors.toList());
	}
	
}

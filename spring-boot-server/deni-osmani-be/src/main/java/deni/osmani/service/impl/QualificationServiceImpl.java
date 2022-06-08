package deni.osmani.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deni.osmani.dao.QualificationDao;
import deni.osmani.dto.OrganizationTypeDto;
import deni.osmani.dto.QualificationDto;
import deni.osmani.entity.OrganizationTypeEntity;
import deni.osmani.entity.QualificationEntity;
import deni.osmani.service.QualificationService;

@Service
public class QualificationServiceImpl implements QualificationService{

	@Autowired
	private QualificationDao qualificationDao;
	
	@Override
	public List<QualificationDto> findAll() {
		List<QualificationEntity> entities = qualificationDao.findAll();
		return entities.stream().map(entity -> {
			return new QualificationDto(entity.getValue());
		}).collect(Collectors.toList());
	}

}

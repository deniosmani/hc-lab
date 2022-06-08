package deni.osmani.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deni.osmani.dao.GenderDao;
import deni.osmani.dto.GenderDto;
import deni.osmani.dto.OrganizationTypeDto;
import deni.osmani.entity.GenderEntity;
import deni.osmani.entity.OrganizationTypeEntity;
import deni.osmani.service.GenderService;

@Service
public class GenderServiceImpl implements GenderService{

	@Autowired
	private GenderDao genderDao;
	
	@Override
	public List<GenderDto> findAll() {
		List<GenderEntity> entities = genderDao.findAll();
		return entities.stream().map(entity -> {
			return new GenderDto(entity.getValue());
		}).collect(Collectors.toList());
	}

}

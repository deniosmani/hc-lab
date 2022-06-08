package deni.osmani.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deni.osmani.dao.MartialStatusDao;
import deni.osmani.dto.MartialStatusDto;
import deni.osmani.entity.MartialStatusEntity;
import deni.osmani.service.MartialStatusService;

@Service
public class MartialStatusServiceImpl implements MartialStatusService{

	@Autowired
	private MartialStatusDao martialStatusDao;
	
	@Override
	public List<MartialStatusDto> findAll() {
		List<MartialStatusEntity> entities = martialStatusDao.findAll();
		return entities.stream().map(entity -> {
			return new MartialStatusDto(entity.getValue());
		}).collect(Collectors.toList());
	}

}

package deni.osmani.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deni.osmani.dao.ExamPriorityDao;
import deni.osmani.dto.ExamPriorityDto;
import deni.osmani.entity.ExamPriorityEntity;
import deni.osmani.service.ExamPriorityService;

@Service
public class ExamPriorityServiceImpl implements ExamPriorityService{
	@Autowired
	private ExamPriorityDao examPriorityDao;

	@Override
	public List<ExamPriorityDto> findAll() {
		List<ExamPriorityEntity> entities = examPriorityDao.findAll();
		return entities.stream().map(entity -> {
			return new ExamPriorityDto(entity.getValue());
		}).collect(Collectors.toList());
	}
	
}

package deni.osmani.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deni.osmani.dao.ExamStatusDao;
import deni.osmani.dto.ExamStatusDto;
import deni.osmani.entity.ExamStatusEntity;
import deni.osmani.service.ExamStatusService;

@Service
public class ExamStatusServiceImpl implements ExamStatusService {

	@Autowired
	private ExamStatusDao examStatusDao;
	
	@Override
	public List<ExamStatusDto> findAll() {
		List<ExamStatusEntity> entities = examStatusDao.findAll();
		return entities.stream().map(entity -> {
			return new ExamStatusDto(entity.getValue());
		}).collect(Collectors.toList());
	}

}

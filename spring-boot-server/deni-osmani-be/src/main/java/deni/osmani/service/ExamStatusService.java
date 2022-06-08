package deni.osmani.service;

import java.util.List;

import deni.osmani.dto.ExamStatusDto;

public interface ExamStatusService {
	List<ExamStatusDto> findAll();
}

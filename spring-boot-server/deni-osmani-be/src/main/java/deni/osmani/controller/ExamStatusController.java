package deni.osmani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import deni.osmani.dto.ExamStatusDto;
import deni.osmani.service.ExamStatusService;


@RestController
@RequestMapping("examstatuses")
@CrossOrigin(origins = "*")
public class ExamStatusController {
	@Autowired
	private ExamStatusService examStatusService;
	@GetMapping
	public List<ExamStatusDto> findAll() {
		return examStatusService.findAll();
	}
}

package deni.osmani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deni.osmani.dto.ExamPriorityDto;
import deni.osmani.service.ExamPriorityService;


@RestController
@RequestMapping("exampriorities")
@CrossOrigin(origins = "*")
public class ExamPriorityController {
	@Autowired
	private ExamPriorityService examPriorityService;
	@GetMapping
	public List<ExamPriorityDto> findAll() {
		return examPriorityService.findAll();
	}
}

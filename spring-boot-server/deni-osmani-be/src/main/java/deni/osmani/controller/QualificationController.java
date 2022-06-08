package deni.osmani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deni.osmani.dto.QualificationDto;
import deni.osmani.service.QualificationService;

@RestController
@RequestMapping("qualifications")
@CrossOrigin(origins = "*")
public class QualificationController {

	@Autowired
	private QualificationService qualificationService;
	@GetMapping
	public List<QualificationDto> findAll() {
		return qualificationService.findAll();
	}
}

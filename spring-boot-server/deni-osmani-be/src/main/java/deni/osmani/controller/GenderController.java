package deni.osmani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deni.osmani.dto.GenderDto;
import deni.osmani.service.GenderService;

@RestController
@RequestMapping("genders")
@CrossOrigin(origins = "*")
public class GenderController {

	@Autowired
	private GenderService genderService;
	@GetMapping
	public List<GenderDto> findAll() {
		return genderService.findAll();
	}
}

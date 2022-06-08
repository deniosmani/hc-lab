package deni.osmani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deni.osmani.dto.ServiceTypeDto;
import deni.osmani.service.ServiceTypeService;

@RestController
@RequestMapping("servicetypes")
@CrossOrigin(origins = "*")
public class ServiceTypeController {
	@Autowired
	private ServiceTypeService serviceTypeService;
	@GetMapping
	public List<ServiceTypeDto> findAll() {
		return serviceTypeService.findAll();
	}

}

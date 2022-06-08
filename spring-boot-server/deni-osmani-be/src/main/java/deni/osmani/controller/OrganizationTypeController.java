package deni.osmani.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import deni.osmani.dto.OrganizationTypeDto;
import deni.osmani.service.OrganizationTypeService;


@RestController
@RequestMapping("organizationTypes")
@CrossOrigin(origins = "*")
public class OrganizationTypeController {
	
	@Autowired
	private OrganizationTypeService organizationTypeService;
	
	@GetMapping
	public List<OrganizationTypeDto> findAll() {
		return organizationTypeService.findAll();
	}
}

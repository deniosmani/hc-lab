package deni.osmani.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import deni.osmani.dao.OrganizationTypeDao;
import deni.osmani.dao.PractitionerDao;
import deni.osmani.dto.OrganizationDto;
import deni.osmani.dto.OrganizationTypeDto;
import deni.osmani.entity.ExaminationEntity;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.PractitionerEntity;
import deni.osmani.service.OrganizationService;
import deni.osmani.service.impl.ExaminationServiceImpl;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;
@RestController
@RequestMapping("organizations")
@CrossOrigin(origins = "*")
public class OrganizationController {
	@Autowired
	private PractitionerDao practitionerDao;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private ExaminationServiceImpl examinationServiceImpl;
	@GetMapping("list")
	public List<OrganizationDto> findAll() {
		return organizationService.findAll();
	}
	@GetMapping("getpract/{id}")
	public Long findPracByOrgId(@PathVariable Long id) {
		List<PractitionerEntity> entities = practitionerDao.getByOrganizationId(id);
		return (long) entities.size();
	}
	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id){
		Optional<OrganizationDto> organization = organizationService.findById(id);
		if (organization.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(organization.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid organization id!");
	}
	@GetMapping("names")
	public List<String> getNames(){
		return organizationService.findNames();
	}

	@GetMapping("filter")
	public ResponseEntity<Page<OrganizationDto>> findAllByName(@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "name") String sortBy,@RequestParam(defaultValue = "asc") String sortOrder) {
		return new ResponseEntity<Page<OrganizationDto>>(organizationService.findByNameContaing(search,pageNo, pageSize, sortBy,sortOrder), new HttpHeaders(),
				HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			organizationService.deleteById(id);
			return ResponseEntity.ok("Deleted");
		} catch (InvalidEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	@PostMapping()
	public ResponseEntity<Object> save(@Valid @RequestBody OrganizationDto organizationDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(organizationService.save(organizationDto));
		} catch (EntityExistsException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody OrganizationDto organizationDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(organizationService.update(organizationDto));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
}

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

import deni.osmani.dao.OrganizationDao;
import deni.osmani.dao.PatientDao;
import deni.osmani.dto.ExaminationDto;
import deni.osmani.dto.PatientDto;
import deni.osmani.dto.PractitionerDto;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.PatientEntity;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;
import deni.osmani.service.ExaminationService;
import deni.osmani.service.OrganizationService;

@RestController
@RequestMapping("examinations")
@CrossOrigin(origins = "*")
public class ExaminationController {
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private ExaminationService examinationService;

	@GetMapping("proba/{id}")
	public List<Long> proba(@PathVariable Long id) {
		return examinationService.getActiveExamsByPractId(id);
	}

	@GetMapping
	public List<ExaminationDto> findAll() {
		return examinationService.findAll();
	}

	@GetMapping("proba")
	public ResponseEntity<Page<ExaminationDto>> findByPractitionerNameAndOrganization(
			@RequestParam(defaultValue = "") String patient, @RequestParam(defaultValue = "") String practitioner,
			@RequestParam(defaultValue = "-1") Long orgId, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "diagnosis") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		if (orgId != -1) {
			Optional<OrganizationEntity> organization = organizationDao.findById(orgId);
			if (organization.isPresent()) {
				return new ResponseEntity<Page<ExaminationDto>>(
						examinationService.findByPractitionerNameContainingAndPatientNameContainingAndOrganization(practitioner,patient, organization.get(),
								pageNo, pageSize, sortBy, sortOrder),
						new HttpHeaders(), HttpStatus.OK);
			} else
				return new ResponseEntity<Page<ExaminationDto>>(Page.empty(), new HttpHeaders(), HttpStatus.OK);
		} else if(!"".equals(practitioner)||!"".equals(patient)){
			return new ResponseEntity<Page<ExaminationDto>>(
					examinationService.findByPractitionerNameContainingAndPatientNameContaining(practitioner,patient, 
							pageNo, pageSize, sortBy, sortOrder),
					new HttpHeaders(), HttpStatus.OK);
		} else
			return new ResponseEntity<Page<ExaminationDto>>(Page.empty(), new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("filter")
	public ResponseEntity<Page<ExaminationDto>> findAllByPatientNameAndOrg(
			@RequestParam(defaultValue = "") String patient, @RequestParam(defaultValue = "-1") Long orgId,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "diagnosis") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder) {
		if (orgId != -1) {
			Optional<OrganizationEntity> organization = organizationDao.findById(orgId);
			if (organization.isPresent()) {
				return new ResponseEntity<Page<ExaminationDto>>(
						examinationService.findByPatientNameContainingAndOrganization(patient, organization.get(),
								pageNo, pageSize, sortBy, sortOrder),
						new HttpHeaders(), HttpStatus.OK);
			} else
				return new ResponseEntity<Page<ExaminationDto>>(Page.empty(), new HttpHeaders(), HttpStatus.OK);
		} else if (!"".equals(patient)) {
			return new ResponseEntity<Page<ExaminationDto>>(
					examinationService.findByPatientNameContaining(patient, pageNo, pageSize, sortBy, sortOrder),
					new HttpHeaders(), HttpStatus.OK);
		} else
			return new ResponseEntity<Page<ExaminationDto>>(Page.empty(), new HttpHeaders(), HttpStatus.OK);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			examinationService.deleteById(id);
			return ResponseEntity.ok("Deleted");
		} catch (InvalidEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ExaminationDto> examination = examinationService.findById(id);
		if (examination.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(examination.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid examination id!");
	}

	@PostMapping()
	public ResponseEntity<Object> save(@Valid @RequestBody ExaminationDto examinationDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examinationService.save(examinationDto));
		} catch (EntityExistsException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id,
			@Valid @RequestBody ExaminationDto examinationDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examinationService.update(examinationDto));
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

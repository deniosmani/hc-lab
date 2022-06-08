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

import deni.osmani.dao.GenderDao;
import deni.osmani.dto.OrganizationDto;
import deni.osmani.dto.PatientDto;
import deni.osmani.entity.GenderEntity;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;
import deni.osmani.service.PatientService;

@RestController
@RequestMapping("patients")
@CrossOrigin(origins = "*")
public class PatientController {

	@Autowired
	private GenderDao genderDao;

	@Autowired
	private PatientService patientService;

	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<PatientDto> patient = patientService.findById(id);
		if (patient.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(patient.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid patient id!");
	}

	@GetMapping("list")
	public List<PatientDto> findAll() {
		return patientService.findAll();
	}


	@GetMapping("proba")
	public ResponseEntity<Page<PatientDto>> findAllByNameAndGender(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String genderInput,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize,
			@RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "asc") String sortOrder) {
		Optional<GenderEntity> gender = genderDao.findById(genderInput);
		if (gender.isEmpty())
			return new ResponseEntity<Page<PatientDto>>(patientService.findByName(name,pageNo, pageSize, sortBy, sortOrder),
					new HttpHeaders(), HttpStatus.OK);
		else
			return new ResponseEntity<Page<PatientDto>>(patientService.findByNameAndGender(name,gender.get(),pageNo, pageSize, sortBy, sortOrder),
					new HttpHeaders(), HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			patientService.deleteById(id);
			return ResponseEntity.ok("Deleted");
		} catch (InvalidEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@PostMapping()
	public ResponseEntity<Object> save(@Valid @RequestBody PatientDto patientDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(patientService.save(patientDto));
		} catch (EntityExistsException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id,
			@Valid @RequestBody PatientDto patientDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(patientService.update(patientDto));
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

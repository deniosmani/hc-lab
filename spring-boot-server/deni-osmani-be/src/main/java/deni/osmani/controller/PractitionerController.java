package deni.osmani.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import deni.osmani.dao.ExaminationDao;
import deni.osmani.dto.OrganizationDto;
import deni.osmani.dto.PractitionerDto;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;
import deni.osmani.service.PractitionerService;

@RestController
@RequestMapping("practitioners")
@CrossOrigin(origins = "*")
public class PractitionerController {

	@Autowired
	private PractitionerService practitionerService;

	@GetMapping("list/{id}")
	public List<PractitionerDto> getDoctorsByOrganizationId(@PathVariable Long id) {
		return practitionerService.getDoctorsByOrganizationId(id);
	}

	@GetMapping("listall/{id}")
	public List<PractitionerDto> getByOrganizationId(@PathVariable Long id) {
		return practitionerService.getByOrganizationId(id);
	}


	@GetMapping("proba")
	public ResponseEntity<Page<PractitionerDto>> findAllBByNameOrUnassigned(@RequestParam(defaultValue = "")String name, @RequestParam(defaultValue = "") String unassigned,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "20") Integer pageSize,
			@RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "asc") String sortOrder) {
		if ("unassigned".equals(unassigned))
			return new ResponseEntity<Page<PractitionerDto>>(
					practitionerService.findUnasignedByName(name,pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(),
					HttpStatus.OK);
		else {
			return new ResponseEntity<Page<PractitionerDto>>(
					practitionerService.findByName(name,pageNo, pageSize, sortBy, sortOrder), new HttpHeaders(), HttpStatus.OK);
		}
	}
	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<PractitionerDto> practitioner = practitionerService.findById(id);
		if (practitioner.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(practitioner.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid practitioner id!");
	}


	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			practitionerService.deleteById(id);
			return ResponseEntity.ok("Deleted");
		} catch (InvalidEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@PostMapping()
	public ResponseEntity<Object> save(@Valid @RequestBody PractitionerDto practitionerDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(practitionerService.save(practitionerDto));
		} catch (EntityExistsException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id,
			@Valid @RequestBody PractitionerDto practitionerDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(practitionerService.update(practitionerDto));
		} catch (RuntimeException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
}

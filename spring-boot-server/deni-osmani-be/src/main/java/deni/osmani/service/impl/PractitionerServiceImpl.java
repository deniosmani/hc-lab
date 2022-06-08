package deni.osmani.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import deni.osmani.converter.PractitionerConverter;
import deni.osmani.dao.ExaminationDao;
import deni.osmani.dao.PatientDao;
import deni.osmani.dao.PractitionerDao;
import deni.osmani.dto.OrganizationDto;
import deni.osmani.dto.PractitionerDto;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.PatientEntity;
import deni.osmani.entity.PractitionerEntity;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;
import deni.osmani.service.PractitionerService;

@Service
public class PractitionerServiceImpl implements PractitionerService{

	@Autowired
	private ExaminationDao examinationDao;
	@Autowired
	private PractitionerDao practitionerDao;
	@Autowired
	private PractitionerConverter practitionerConverter;
	@Autowired
	private PatientDao patientDao;
	
	@Override
	public Page<PractitionerDto> findAll(Integer pageNo, Integer pageSize, String sortBy,String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortBy));
		Page<PractitionerDto> entites = practitionerDao.findAll(pageable).map(practitionerConverter::toDto);
		return entites;
	}
	@Override
	public Page<PractitionerDto> findAllUnasigned(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortBy));
		Page<PractitionerDto> entites = practitionerDao.findByOrganizationIdIsNull(pageable).map(practitionerConverter::toDto);
		return entites;
	}
	@Override
	public Page<PractitionerDto> findByName(String name, Integer pageNo, Integer pageSize, String sortBy,
			String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortBy));
		Page<PractitionerDto> entites = practitionerDao.findByNameContaining(name,pageable).map(practitionerConverter::toDto);
		return entites;
	}
	@Override
	public Page<PractitionerDto> findUnasignedByName(String name, Integer pageNo, Integer pageSize, String sortBy,
			String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortBy));
		Page<PractitionerDto> entites = practitionerDao.findByNameContainingAndOrganizationIdIsNull(name,pageable).map(practitionerConverter::toDto);
		return entites;
	}
	@Transactional
	@Override
	public void deleteById(Long id) throws InvalidEntityException {
		Optional<PractitionerEntity> entity = practitionerDao.findById(id);
		if(entity.isEmpty()) {
			throw new InvalidEntityException("Entity does not exists");
		}
		List<Long> activeExaminations = examinationDao.getActivePractitionersByPractitionerId(id);
		if(activeExaminations.get(0)>0) {
			throw new InvalidEntityException("There is on-going examination, you cannot delete this practitioner.");
		}
		//set primary care provider on null for patients that primary care provider was deleted practitioner
		List<PatientEntity> patients = patientDao.getByPractionerId(id);
		for(PatientEntity patient:patients) {
			patient.setPrimaryCareProvider(null);
			patientDao.save(patient);
		}
		practitionerDao.deleteById(id);
		
	}
	@Transactional
	@Override
	public PractitionerDto save(PractitionerDto practitionerDto) throws EntityExistsException {
		Optional<PractitionerEntity> entity = practitionerDao.findById(practitionerDto.getId());
		if (entity.isPresent()) {
			throw new EntityExistsException(entity.get(), "Organization already exists!");
		}
		PractitionerEntity city = practitionerDao.save(practitionerConverter.toEntity(practitionerDto));
		return practitionerConverter.toDto(city);
	}

	@Override
	public Optional<PractitionerDto> findById(Long id) {
		Optional<PractitionerEntity> entity = practitionerDao.findById(id);
		if (entity.isPresent()) {
			return Optional.of(practitionerConverter.toDto(entity.get()));
		}
		return Optional.empty();
	}
	@Transactional
	@Override
	public PractitionerDto update(PractitionerDto practitionerDto) throws RuntimeException {
		Optional<PractitionerEntity> entity = practitionerDao.findById(practitionerDto.getId());
		if (!entity.isPresent()) {
			throw new RuntimeException("Practitioner does not exist: " + practitionerDto.getName());
		}
		//TODO CHECK IS THERE ORGANIZATION
		PractitionerEntity entityToSave = practitionerConverter.toEntity(practitionerDto);
		PractitionerEntity entitySaved = practitionerDao.save(entityToSave);
		return practitionerConverter.toDto(entitySaved);
	}

	@Override
	public List<PractitionerDto> findAll() {
		List<PractitionerEntity> entities = practitionerDao.findAll();
		return entities.stream().map(entity -> {
			return practitionerConverter.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public List<PractitionerDto> getByOrganizationId(Long id) {
		List<PractitionerEntity> entities = practitionerDao.getByOrganizationId(id);
		return entities.stream().map(entity -> {
			return practitionerConverter.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public List<PractitionerDto> getDoctorsByOrganizationId(Long id) {
		List<PractitionerEntity> entities = practitionerDao.getDoctorsByOrganizationId(id);
		return entities.stream().map(entity -> {
			return practitionerConverter.toDto(entity);
		}).collect(Collectors.toList());
	}




	
	
}

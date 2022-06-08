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

import deni.osmani.converter.ExaminationConverter;
import deni.osmani.dao.ExaminationDao;
import deni.osmani.dto.ExaminationDto;
import deni.osmani.dto.PatientDto;
import deni.osmani.entity.ExaminationEntity;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.PatientEntity;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;
import deni.osmani.service.ExaminationService;

@Service
public class ExaminationServiceImpl implements ExaminationService{
	
	@Autowired
	private ExaminationDao examinationDao;
	@Autowired
	private ExaminationConverter examinationConverter;
	
	@Override
	public List<ExaminationDto> findAll() {
		List<ExaminationEntity> entities = examinationDao.findAll();
		return entities.stream().map(entity -> {
			return examinationConverter.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public Page<ExaminationDto> findAll(Integer pageNo, Integer pageSize, String sortBy,String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortBy));
		Page<ExaminationDto> entites = examinationDao.findAll(pageable).map(examinationConverter::toDto);
		return entites;
	}
	@Override
	public Page<ExaminationDto> findByPractitionerNameContainingAndPatientNameContaining(String practitioner,
			String patient, Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortBy));
		Page<ExaminationDto> entites = examinationDao.findDistinctByPractitioners_NameContainingAndPatientNameContaining(practitioner,patient,pageable).map(examinationConverter::toDto);
		return entites;
	}

	@Override
	public Page<ExaminationDto> findByPractitionerNameContainingAndPatientNameContainingAndOrganization(
			String practitioner, String patient, OrganizationEntity organization, Integer pageNo, Integer pageSize,
			String sortBy, String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortBy));
		Page<ExaminationDto> entites = examinationDao.findDistinctByPractitioners_NameContainingAndPatientNameContainingAndOrganization(practitioner,patient,organization,pageable).map(examinationConverter::toDto);
		return entites;
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) throws InvalidEntityException {
		Optional<ExaminationEntity> entity = examinationDao.findById(id);
		if(entity.isEmpty()) {
			throw new InvalidEntityException("Entity does not exists");
		}
		examinationDao.deleteById(id);
		
	}

	@Override
	public Optional<ExaminationDto> findById(Long id) {
		Optional<ExaminationEntity> entity = examinationDao.findById(id);
		if (entity.isPresent()) {
			return Optional.of(examinationConverter.toDto(entity.get()));
		}
		return Optional.empty();
	}
	@Transactional
	@Override
	public ExaminationDto save(ExaminationDto examinationDto) throws EntityExistsException {
		Optional<ExaminationEntity> entity = examinationDao.findById(examinationDto.getId());
		if (entity.isPresent()) {
			throw new EntityExistsException(entity.get(), "Examination already exists!");
		}
		ExaminationEntity examination = examinationDao.save(examinationConverter.toEntity(examinationDto));
		return examinationConverter.toDto(examination);
	}
	@Transactional
	@Override
	public ExaminationDto update(ExaminationDto examinationDto) throws RuntimeException {
		Optional<ExaminationEntity> entity = examinationDao.findById(examinationDto.getId());
		if (!entity.isPresent()) {
			throw new RuntimeException("Examinaiton with id does not exist: " + examinationDto.getId());
		}
		ExaminationEntity entityToSave = examinationConverter.toEntity(examinationDto);
		ExaminationEntity entitySaved = examinationDao.save(entityToSave);
		return examinationConverter.toDto(entitySaved);
	}

	@Override
	public List<Long> getActiveExamsByPractId(Long id) {
		return examinationDao.getActivePractitionersByPractitionerId(id);
	}

	@Override
	public Page<ExaminationDto> findByOrganization(OrganizationEntity organization, Integer pageNo, Integer pageSize,
			String sortBy, String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		
		Page<ExaminationDto> entites = examinationDao.findByOrganization(organization,pageable).map(examinationConverter::toDto);
		
		return entites;
	}

	@Override
	public Page<ExaminationDto> findByPatient(PatientEntity patient, Integer pageNo, Integer pageSize, String sortBy,
			String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<ExaminationDto> entites = examinationDao.findByPatient(patient,pageable).map(examinationConverter::toDto);
		return entites;
	}

	@Override
	public Page<ExaminationDto> findByPatientNameContaining(String patient, Integer pageNo, Integer pageSize,
			String sortBy, String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<ExaminationDto> entites = examinationDao.findByPatientNameContaining(patient,pageable).map(examinationConverter::toDto);
		return entites;
	}

	@Override
	public Page<ExaminationDto> findByPatientNameContainingAndOrganization(String patient,
			OrganizationEntity organization, Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<ExaminationDto> entites = examinationDao.findByPatientNameContainingAndOrganization(patient,organization,pageable).map(examinationConverter::toDto);
		return entites;
	}






	
	
	
}

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

import deni.osmani.converter.PatientConverter;
import deni.osmani.dao.ExamStatusDao;
import deni.osmani.dao.ExaminationDao;
import deni.osmani.dao.PatientDao;
import deni.osmani.dto.PatientDto;

import deni.osmani.entity.ExamStatusEntity;
import deni.osmani.entity.ExaminationEntity;
import deni.osmani.entity.GenderEntity;
import deni.osmani.entity.PatientEntity;

import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;
import deni.osmani.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private ExamStatusDao statusDao;
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private PatientConverter patientConverter;
	@Autowired
	private ExaminationDao examinationDao;
	
	@Override
	public Page<PatientDto> findAll(Integer pageNo, Integer pageSize, String sortBy,String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<PatientDto> entites = patientDao.findAll(pageable).map(patientConverter::toDto);
		return entites;
	}
	@Override
	public Page<PatientDto> findByNameAndGender(String patient, GenderEntity gender, Integer pageNo, Integer pageSize,
			String sortBy, String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<PatientDto> entites = patientDao.findByNameContainingAndGender(patient,gender,pageable).map(patientConverter::toDto);
		return entites;
	}

	@Override
	public Page<PatientDto> findByName(String string, Integer pageNo, Integer pageSize, String sortBy,
			String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<PatientDto> entites = patientDao.findByNameContaining(string,pageable).map(patientConverter::toDto);
		return entites;
	}

	@Override
	public Page<PatientDto> findByGender(GenderEntity gender, Integer pageNo, Integer pageSize, String sortBy,
			String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
		Page<PatientDto> entites = patientDao.findByGender(gender,pageable).map(patientConverter::toDto);
		return entites;
	}
	@Transactional
	@Override
	public void deleteById(Long id) throws InvalidEntityException {
		Optional<PatientEntity> entity = patientDao.findById(id);
		List<ExaminationEntity> examinationsByPatient = examinationDao.getByPatientId(id);
		if(!examinationsByPatient.isEmpty()) {
			throw new InvalidEntityException("There is on-going examination, you can not delete this patient.");
		}
		if(entity.isEmpty()) {
			throw new InvalidEntityException("Entity does not exists");
		}
		//set status entered-in-error in examinations that has deleted patient
		List<ExaminationEntity> allExaminations = examinationDao.getAllByPatientId(id);
		for(ExaminationEntity exam:allExaminations) {
			Optional<ExamStatusEntity> status=statusDao.findById("entered-in-error");
			exam.setStatus(status.get());
			examinationDao.save(exam);
		}
		patientDao.deleteById(id);
		
	}
	@Transactional
	@Override
	public PatientDto save(PatientDto patientDto) throws EntityExistsException {
		Optional<PatientEntity> entity = patientDao.findById(patientDto.getId());
		if (entity.isPresent()) {
			throw new EntityExistsException(entity.get(), "Organization already exists!");
		}
		PatientEntity patient = patientDao.save(patientConverter.toEntity(patientDto));
		return patientConverter.toDto(patient);
	}

	@Override
	public Optional<PatientDto> findById(Long id) {
		Optional<PatientEntity> entity = patientDao.findById(id);
		if (entity.isPresent()) {
			return Optional.of(patientConverter.toDto(entity.get()));
		}
		return Optional.empty();
	}
	@Transactional
	@Override
	public PatientDto update(PatientDto patientDto) throws RuntimeException {
		Optional<PatientEntity> entity = patientDao.findById(patientDto.getId());
		if (!entity.isPresent()) {
			throw new RuntimeException("Practitioner does not exist: " + patientDto.getName());
		}
		//TODO CHECK IS THERE ORGANIZATION AND PRACTITIONER
		PatientEntity entityToSave = patientConverter.toEntity(patientDto);
		PatientEntity entitySaved = patientDao.save(entityToSave);
		return patientConverter.toDto(entitySaved);
	}

	@Override
	public List<PatientDto> findAll() {
		List<PatientEntity> entities = patientDao.findAll();
		return entities.stream().map(entity -> {
			return patientConverter.toDto(entity);
		}).collect(Collectors.toList());

	}






}

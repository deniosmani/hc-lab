package deni.osmani.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import deni.osmani.converter.OrganizationConverter;
import deni.osmani.dao.ExamStatusDao;
import deni.osmani.dao.ExaminationDao;
import deni.osmani.dao.OrganizationDao;
import deni.osmani.dao.PatientDao;
import deni.osmani.dao.PractitionerDao;
import deni.osmani.dto.OrganizationDto;
import deni.osmani.dto.OrganizationTypeDto;
import deni.osmani.entity.ExamStatusEntity;
import deni.osmani.entity.ExaminationEntity;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.OrganizationTypeEntity;
import deni.osmani.entity.PatientEntity;
import deni.osmani.entity.PractitionerEntity;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;
import deni.osmani.service.OrganizationService;



@Service
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired
	private OrganizationConverter organizationConverter;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private PractitionerDao practitionerDao;
	@Autowired
	private ExaminationDao examinationDao;
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private ExamStatusDao statusDao;
	
	@Override
	public Page<OrganizationDto> findAll(Integer pageNo, Integer pageSize, String sortBy,String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortBy));
		Page<OrganizationDto> entites = organizationDao.findAll(pageable).map(organizationConverter::toDto);
		return entites;
	}
	@Override
	public Page<OrganizationDto> findByNameContaing(String search,Integer pageNo, Integer pageSize, String sortBy,String sortOrder) {
		Sort.Direction direction="asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;;
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction,sortBy));
		Page<OrganizationDto> entites = organizationDao.findByNameContaining(search,pageable).map(organizationConverter::toDto);
		return entites;
	}
	@Transactional
	@Override
	public void deleteById(Long id) throws InvalidEntityException {
		Optional<OrganizationEntity> entity = organizationDao.findById(id);
		List<ExaminationEntity> examinationsByOrganization = examinationDao.getByOrganizationId(id);
		if(!examinationsByOrganization.isEmpty()) {
			throw new InvalidEntityException("There is on-going examination, you can not delete this organization.");
		}
		if(entity.isEmpty()) {
			throw new InvalidEntityException("Entity does not exists");
		}
		//set organization on null for practitioners that organization was deleted organization
		List<PractitionerEntity> practitionerEntities = practitionerDao.getByOrganizationId(id);
		for(PractitionerEntity prac:practitionerEntities) {
			prac.setOrganization(null);
			practitionerDao.save(prac);
		}
		//same for examination
		List<ExaminationEntity> allExaminations = examinationDao.getAllByOrganizationId(id);
		for(ExaminationEntity prac:allExaminations) {
			Optional<ExamStatusEntity> status=statusDao.findById("entered-in-error");
			prac.setStatus(status.get());
			examinationDao.save(prac);
		}
		//same for patients
		List<PatientEntity> patients = patientDao.getByOrganizationId(id);
		for(PatientEntity prac:patients) {
			prac.setOrganization(null);
			patientDao.save(prac);
		}
		organizationDao.deleteById(id);
	}
	@Transactional
	@Override
	public OrganizationDto save(OrganizationDto organizationDto) throws EntityExistsException {
		Optional<OrganizationEntity> entity = organizationDao.findById(organizationDto.getId());
		if (entity.isPresent()) {
			throw new EntityExistsException(entity.get(), "Organization already exists!");
		}
		OrganizationEntity city = organizationDao.save(organizationConverter.toEntity(organizationDto));
		return organizationConverter.toDto(city);
	}

	@Override
	public Optional<OrganizationDto> findById(Long id) {
		Optional<OrganizationEntity> entity = organizationDao.findById(id);
		if (entity.isPresent()) {
			return Optional.of(organizationConverter.toDto(entity.get()));
		}
		return Optional.empty();
	}
	@Transactional
	@Override
	public OrganizationDto update(OrganizationDto organizationDto) throws RuntimeException {
		Optional<OrganizationEntity> entity = organizationDao.findById(organizationDto.getId());
		if (!entity.isPresent()) {
			throw new RuntimeException("Organization does not exist: " + organizationDto.getName());
		}
		
		OrganizationEntity entityToSave = organizationConverter.toEntity(organizationDto);
		OrganizationEntity entitySaved = organizationDao.save(entityToSave);
		return organizationConverter.toDto(entitySaved);
	}

	@Override
	public List<OrganizationDto> findAll() {
		List<OrganizationEntity> entities = organizationDao.findAll();
		return entities.stream().map(entity -> {
			return organizationConverter.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public List<String> findNames() {
		return organizationDao.findNames();
	}


	
}

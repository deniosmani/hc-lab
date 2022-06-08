package deni.osmani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import deni.osmani.dto.ExaminationDto;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.PatientEntity;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;



public interface ExaminationService {
	List<ExaminationDto> findAll();
	Page<ExaminationDto> findAll(Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	public void deleteById(Long id) throws InvalidEntityException;
	Optional<ExaminationDto> findById(Long id);
	ExaminationDto save(ExaminationDto examinationDto)  throws EntityExistsException;
	ExaminationDto update(ExaminationDto examinationDto) throws RuntimeException;
	List<Long> getActiveExamsByPractId(Long id);
	Page<ExaminationDto> findByOrganization(OrganizationEntity organization,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	Page<ExaminationDto> findByPatient(PatientEntity patient,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	Page<ExaminationDto> findByPatientNameContaining(String patient,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	Page<ExaminationDto> findByPatientNameContainingAndOrganization(String patient,OrganizationEntity organization,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	Page<ExaminationDto> findByPractitionerNameContainingAndPatientNameContaining(String practitioner,String patient,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	Page<ExaminationDto> findByPractitionerNameContainingAndPatientNameContainingAndOrganization(String practitioner,String patient,OrganizationEntity organization , Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
}

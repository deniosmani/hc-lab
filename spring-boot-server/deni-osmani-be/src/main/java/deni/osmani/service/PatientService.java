package deni.osmani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;


import deni.osmani.dto.PatientDto;
import deni.osmani.entity.GenderEntity;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;

public interface PatientService {
	Page<PatientDto> findAll(Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	public void deleteById(Long id) throws InvalidEntityException;
	PatientDto save(PatientDto patientDto)  throws EntityExistsException;
	Optional<PatientDto> findById(Long id);
	PatientDto update(PatientDto cityDto) throws RuntimeException;
	List<PatientDto> findAll();
	Page<PatientDto> findByGender(GenderEntity gender,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	Page<PatientDto> findByNameAndGender(String patient,GenderEntity gender,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	Page<PatientDto> findByName(String string,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
}

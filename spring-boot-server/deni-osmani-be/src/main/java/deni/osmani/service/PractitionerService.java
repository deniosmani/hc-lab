package deni.osmani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import deni.osmani.dto.PractitionerDto;
import deni.osmani.entity.PractitionerEntity;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;

public interface PractitionerService {
	Page<PractitionerDto> findAll(Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	public void deleteById(Long id) throws InvalidEntityException;
	PractitionerDto save(PractitionerDto practitionerDto)  throws EntityExistsException;
	Optional<PractitionerDto> findById(Long id);
	PractitionerDto update(PractitionerDto cityDto) throws RuntimeException;
	List<PractitionerDto> findAll();
	List<PractitionerDto> getByOrganizationId(Long id);
	List<PractitionerDto> getDoctorsByOrganizationId(Long id);
	Page<PractitionerDto> findByName(String name,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	Page<PractitionerDto> findAllUnasigned(Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	Page<PractitionerDto> findUnasignedByName(String name,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
}

package deni.osmani.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import deni.osmani.dto.OrganizationDto;
import deni.osmani.exception.EntityExistsException;
import deni.osmani.exception.InvalidEntityException;



public interface OrganizationService {
	Page<OrganizationDto> findAll(Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
	public void deleteById(Long id) throws InvalidEntityException;
	OrganizationDto save(OrganizationDto organizationDto)  throws EntityExistsException;
	Optional<OrganizationDto> findById(Long id);
	OrganizationDto update(OrganizationDto cityDto) throws RuntimeException;
	List<OrganizationDto> findAll();
	List<String> findNames();
	Page<OrganizationDto> findByNameContaing(String search,Integer pageNo, Integer pageSize, String sortBy,String sortOrder);
}

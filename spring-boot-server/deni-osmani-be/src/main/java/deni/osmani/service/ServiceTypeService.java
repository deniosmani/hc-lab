package deni.osmani.service;

import java.util.List;

import deni.osmani.dto.QualificationDto;
import deni.osmani.dto.ServiceTypeDto;

public interface ServiceTypeService {
	List<ServiceTypeDto> findAll();
}

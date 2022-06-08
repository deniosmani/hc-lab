package deni.osmani.service;

import java.util.List;

import deni.osmani.dto.MartialStatusDto;

public interface MartialStatusService {
	List<MartialStatusDto> findAll();
}

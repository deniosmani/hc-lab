package deni.osmani.converter;

import deni.osmani.dto.Dto;
import deni.osmani.entity.Entity;

public interface GenericConverter <D extends Dto, E extends Entity>{
	E toEntity(D dto);

	D toDto(E entity);
}

package deni.osmani.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.PractitionerEntity;

@Repository
public interface PractitionerDao extends JpaRepository<PractitionerEntity, Long>{
	@Query(value="select * from table_practitioner tp where tp.organization_id=:id and tp.active=true",nativeQuery=true)	
	List<PractitionerEntity> getByOrganizationId(@Param("id") Long id);
	@Query(value="select * from table_practitioner tp where tp.organization_id=:id and tp.qualification_value='Doctor of Medicine' and tp.active=true",nativeQuery=true)	
	List<PractitionerEntity> getDoctorsByOrganizationId(@Param("id") Long id);
	
	Page<PractitionerEntity> findByOrganizationIdIsNull(Pageable pageable);
	Page<PractitionerEntity> findByNameContaining(String name,Pageable pageable);
	Page<PractitionerEntity> findByNameContainingAndOrganizationIdIsNull(String name,Pageable pageable);
}

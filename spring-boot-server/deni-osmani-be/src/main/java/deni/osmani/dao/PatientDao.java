package deni.osmani.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.GenderEntity;
import deni.osmani.entity.PatientEntity;
import deni.osmani.entity.PractitionerEntity;

@Repository
public interface PatientDao extends JpaRepository<PatientEntity, Long> {
	@Query(value="select * from table_patient tp where tp.organization=:id and tp.active=true",nativeQuery=true)	
	List<PatientEntity> getByOrganizationId(@Param("id") Long id);
	@Query(value="select * from table_patient tp where tp.primary_care_provider=:id and tp.active=true",nativeQuery=true)	
	List<PatientEntity> getByPractionerId(@Param("id") Long id);
	
	Page<PatientEntity> findByGender(GenderEntity gender,Pageable pageable);
	Page<PatientEntity> findByNameContaining(String patient,Pageable pageable);
	Page<PatientEntity> findByNameContainingAndGender(String patient,GenderEntity gender,Pageable pageable);
}

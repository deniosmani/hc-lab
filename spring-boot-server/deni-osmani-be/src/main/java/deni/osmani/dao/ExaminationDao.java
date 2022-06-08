package deni.osmani.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.ExaminationEntity;
import deni.osmani.entity.OrganizationEntity;
import deni.osmani.entity.PatientEntity;

@Repository
public interface ExaminationDao extends JpaRepository<ExaminationEntity,Long>{

	@Query(value="select * from table_examination te where te.organization=:id and te.status_value='in-progress'",nativeQuery=true)
	List<ExaminationEntity> getByOrganizationId(@Param("id")Long id);
	@Query(value="select * from table_examination te where te.organization=:id and te.status_value<>'entered-in-error'",nativeQuery=true)
	List<ExaminationEntity> getAllByOrganizationId(@Param("id")Long id);
	@Query(value="select * from table_examination te where te.patient=:id and te.status_value='in-progress'",nativeQuery=true)
	List<ExaminationEntity> getByPatientId(@Param("id")Long id);
	@Query(value="select * from table_examination te where te.patient=:id and te.status_value<>'entered-in-error'",nativeQuery=true)
	List<ExaminationEntity> getAllByPatientId(@Param("id")Long id);
	@Query(value="SELECT COUNT(*) FROM table_examination te JOIN table_examination_practitioners tep WHERE te.id=tep.examination_entity_id AND te.status_value='in-progress' AND tep.practitioners_id=:id",nativeQuery=true)
	List<Long> getActivePractitionersByPractitionerId(@Param("id")Long id);

	Page<ExaminationEntity> findByPatient(PatientEntity patient,Pageable pageable);
	Page<ExaminationEntity> findByOrganization(OrganizationEntity organization,Pageable pageable);
	Page<ExaminationEntity> findByPatientNameContaining(String patient,Pageable pageable);
	Page<ExaminationEntity> findByPatientNameContainingAndOrganization(String patient,OrganizationEntity organization,Pageable pageable);
	
	Page<ExaminationEntity> findDistinctByPractitioners_NameContainingAndPatientNameContaining(String practitioner,String patient,Pageable pageable); 
	Page<ExaminationEntity> findDistinctByPractitioners_NameContainingAndPatientNameContainingAndOrganization(String practitioner,String patient,OrganizationEntity organization,Pageable pageable); 
	
}

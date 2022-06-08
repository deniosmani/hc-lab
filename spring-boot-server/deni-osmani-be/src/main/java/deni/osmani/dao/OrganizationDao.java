package deni.osmani.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.OrganizationEntity;

@Repository
public interface OrganizationDao extends JpaRepository<OrganizationEntity, Long>{
	
	@Query(value="select name from table_organization",nativeQuery=true)
	List<String> findNames();
	
	Page<OrganizationEntity> findByNameContaining(String search, Pageable pageable); 
}

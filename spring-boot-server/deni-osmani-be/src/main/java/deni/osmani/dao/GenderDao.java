package deni.osmani.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.GenderEntity;

@Repository
public interface GenderDao extends JpaRepository<GenderEntity, String> {
	
}

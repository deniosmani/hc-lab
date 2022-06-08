package deni.osmani.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.QualificationEntity;

@Repository
public interface QualificationDao extends JpaRepository<QualificationEntity, String> {

}

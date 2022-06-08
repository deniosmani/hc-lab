package deni.osmani.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.ExamPriorityEntity;

@Repository
public interface ExamPriorityDao extends JpaRepository<ExamPriorityEntity, String> {
	
}

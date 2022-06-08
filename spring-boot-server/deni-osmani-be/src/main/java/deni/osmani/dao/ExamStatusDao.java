package deni.osmani.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.ExamStatusEntity;

@Repository
public interface ExamStatusDao extends JpaRepository<ExamStatusEntity, String> {

}

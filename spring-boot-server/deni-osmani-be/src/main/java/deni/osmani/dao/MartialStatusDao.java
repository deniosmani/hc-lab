package deni.osmani.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.MartialStatusEntity;

@Repository
public interface MartialStatusDao extends JpaRepository<MartialStatusEntity, String>{

}

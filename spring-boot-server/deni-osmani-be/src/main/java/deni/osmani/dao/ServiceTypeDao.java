package deni.osmani.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.ServiceTypeEntity;

@Repository
public interface ServiceTypeDao extends JpaRepository<ServiceTypeEntity, String>{

}

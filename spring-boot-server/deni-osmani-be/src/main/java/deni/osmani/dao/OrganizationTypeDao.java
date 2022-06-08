package deni.osmani.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.OrganizationTypeEntity;

@Repository
public interface OrganizationTypeDao extends JpaRepository<OrganizationTypeEntity, String>{


}

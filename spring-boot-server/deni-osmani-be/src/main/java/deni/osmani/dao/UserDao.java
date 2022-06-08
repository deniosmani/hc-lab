package deni.osmani.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import deni.osmani.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByUsername(String username);
	Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}

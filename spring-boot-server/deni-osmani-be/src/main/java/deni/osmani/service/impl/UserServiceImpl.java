package deni.osmani.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deni.osmani.converter.UserConverter;
import deni.osmani.dao.UserDao;
import deni.osmani.dto.UserDto;
import deni.osmani.entity.UserEntity;
import deni.osmani.exception.BadCredentialsException;
import deni.osmani.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public UserDto login(String username, String password) throws BadCredentialsException {
		Optional<UserEntity> user = userDao.findByUsernameAndPassword(username, password);
		if (!user.isPresent()) {
			throw new BadCredentialsException("Invalid user!");
		}
		return userConverter.toDto(user.get());
	}

}

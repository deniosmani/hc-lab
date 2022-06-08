package deni.osmani.service;

import deni.osmani.dto.UserDto;
import deni.osmani.exception.BadCredentialsException;

public interface UserService {
	UserDto login(String username, String password) throws BadCredentialsException;
}

package deni.osmani.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import deni.osmani.dao.UserDao;
import deni.osmani.entity.UserEntity;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userDao.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exist!"));
		return new MyUserDetails(user.get().getUsername(), user.get().getPassword(), user.get().getFirstname(),
				user.get().getLastname());
	}

}

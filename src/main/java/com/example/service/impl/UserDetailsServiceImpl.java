package com.example.service.impl;

import com.example.exception.UsernameNotFoundException;
import com.example.model.Users;
import com.example.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user = usersRepository.findById(username);

		if (user == null||user.isEmpty()) {
			throw new UsernameNotFoundException("Invalid username or password");
		}

		return new User(username, user.get().getPassword(), true, true, true,
				true, AuthorityUtils.createAuthorityList(user.get().getRole()));
	}


	/**
	 * Add some users at application startup for testing
	 */
	@PostConstruct
	public void loadUsers() {
		List<Users> users = Arrays.asList(
							new Users("user", "password", "USER"),
							new Users("admin", "password", "ADMIN"));
		usersRepository.saveAll(users);
	}

}

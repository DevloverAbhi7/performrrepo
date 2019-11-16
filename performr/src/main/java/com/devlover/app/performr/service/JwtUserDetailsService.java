package com.devlover.app.performr.service;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devlover.app.performr.dao.UserDao;
import com.devlover.app.performr.model.CommonResponse;
import com.devlover.app.performr.model.Users;
import com.devlover.app.performr.util.Constants;
import com.devlover.app.performr.util.CustomException;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional <Users> user = userDao.findByUsername(username);
		if (!user.isPresent()) {
			throw new CustomException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
				new ArrayList<>());
	}

	public CommonResponse save(Users user) {
		String securepass=bcryptEncoder.encode(user.getPassword()); 
		user.setPassword(securepass);
	userDao.save(user);	 
	return  new CommonResponse(Constants.SUCCESSCODE, Constants.SUCCESSMESSAGE,user);
	}
}
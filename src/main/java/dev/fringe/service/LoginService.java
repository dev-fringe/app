package dev.fringe.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.fringe.entity.User;
import dev.fringe.entity.support.AuthUser;
import dev.fringe.security.UserGrantedAuthority;

@Service
public class LoginService implements UserDetailsService {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private Map<String, String> roles = new HashMap<String, String>();
	public LoginService() {
		roles.put("ROLE_USER", "Customer");
		roles.put("ROLE_ADMIN", "Admin");
	}

	public Map<String, String> getRoles() {
		return roles;
	}

	public UserDetails loadUserByUsername(String username) {
		if (username != null && !username.equals("")) {
			User user = userService.get(username);
			if (user == null) {
				return null;
			}
			GrantedAuthority grantedAuth = new UserGrantedAuthority(user.getRole());
			return new AuthUser(user.getId(), user.getEmail(), encoder.encode(user.getPassword()),
					new GrantedAuthority[] { grantedAuth });
		} else {
			return null;
		}
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

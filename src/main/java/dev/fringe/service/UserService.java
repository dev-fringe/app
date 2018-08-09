package dev.fringe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fringe.entity.User;
import dev.fringe.repository.UserRepository;

/**
 * @author dev-frine
 */
@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public void save() {
		repo.save();
	}

	public List<User> list(User user) {
		return repo.list(user);
	}
	public Long count(User user) {
		return repo.count(user);
	}

	public Map<String, Object> get(User user) {
		Map<String, Object> map = new HashMap<>();
		user.setRowcount(this.count(user));
		System.out.println(this.list(user));
		map.put("user", user);
		map.put("users", this.list(user));
		return map;
	}
 
	public void add(User user) {
		repo.persist(user);
	}
}

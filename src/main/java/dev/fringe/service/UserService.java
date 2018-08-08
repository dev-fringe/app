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
		System.out.println(user);
		return repo.list(user);
	}
	public int count(User user) {
		return repo.count(user);
	}

	public Map<String, Object> get(User user) {
		Map<String, Object> map = new HashMap<>();
		user.setRowcount(this.count(user));
		map.put("user", user);
		map.put("users", this.list(user));
		return map;
	}
}

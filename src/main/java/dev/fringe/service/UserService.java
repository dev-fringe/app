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

	public List<User> get(int offset, int maxResults) {
		return repo.list(offset, maxResults);
	}
	public int count() {
		return repo.count();
	}

	public Map<String, Object> get(User user) {
		Map<String, Object> map = new HashMap<>();
		user.setRowcount(this.count());
		map.put("user", user);
		map.put("users", repo.list(user.getFrom(), user.getSize()));
		return map;
	}
}

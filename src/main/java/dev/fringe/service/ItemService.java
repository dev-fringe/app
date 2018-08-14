package dev.fringe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fringe.entity.Item;
import dev.fringe.entity.User;
import dev.fringe.repository.ItemRepository;

/**
 * @author dev-fringe
 */
@Service
public class ItemService {

	@Autowired
	private ItemRepository repo;

	public List<User> list(Item item) {
		return repo.list(item);
	}
	
	public Long count(Item item) {
		return repo.count(item);
	}

	public Map<String, Object> get(Item item) {
		Map<String, Object> map = new HashMap<>();
		item.setRowcount(this.count(item));
		System.out.println(this.list(item));
		map.put("item", item);
		map.put("items", this.list(item));
		return map;
	}
	
	public User get(String username) {
		return repo.get(username);
	}
 
	public void add(Item item) {
		repo.persist(item);
	}
}

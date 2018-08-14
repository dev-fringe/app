package dev.fringe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fringe.entity.Category;
import dev.fringe.entity.User;
import dev.fringe.repository.CategoryRepository;

/**
 * @author dev-fringe
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public List<User> list(Category Category) {
		return repo.list(Category);
	}
	
	public Long count(Category Category) {
		return repo.count(Category);
	}

	public Map<String, Object> get(Category category) {
		Map<String, Object> map = new HashMap<>();
		category.setRowcount(this.count(category));
		System.out.println(this.list(category));
		map.put("category", category);
		map.put("categorys", this.list(category));
		return map;
	}
	
	public User get(String catid) {
		return repo.get(catid);
	}
 
	public void add(Category Category) {
		repo.persist(Category);
	}
}

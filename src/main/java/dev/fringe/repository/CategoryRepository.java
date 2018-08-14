package dev.fringe.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.fringe.entity.Category;
import dev.fringe.entity.User;
import dev.fringe.support.AbstractRepository;

/**
 * @author dev-frine
 */
@Repository
@Transactional
public class CategoryRepository extends AbstractRepository<Category, Long> {


	@SuppressWarnings("unchecked")
	public List<User> list(Category category) {
		Integer offset = category.getFrom();
		Integer maxResults = category.getSize();
		return getSession().createQuery("From Category where CATID LIKE :CATID")
				.setParameter("CATID", "%" + category.getCategoryId() + "%").setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : 10).list();
	}

	public Long count(Category category) {
		return (Long) getSession().createQuery("select count(*) from Category where CATID LIKE :CATID")
				.setParameter("CATID", "%" + category.getCategoryId() + "%").uniqueResult();
	}

	public void persist(Category category) {
		getSession().save(category);
	}

	public User get(String catid) {
		return (User) getSession().createQuery("FROM Category WHERE CATID=:CATID").setParameter("CATID", "%" + catid + "%").getSingleResult();
	}

}

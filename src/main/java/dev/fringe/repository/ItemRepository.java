package dev.fringe.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.fringe.entity.Item;
import dev.fringe.entity.User;
import dev.fringe.support.AbstractRepository;

/**
 * @author dev-frine
 */
@Repository
@Transactional
public class ItemRepository extends AbstractRepository<Item, Long> {


	@SuppressWarnings("unchecked")
	public List<User> list(Item item) {
		Integer offset = item.getFrom();
		Integer maxResults = item.getSize();
		return getSession().createQuery("From Item where productid LIKE :productid")
				.setParameter("productid", "%" + item.getProductId() + "%").setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : 10).list();
	}

	public Long count(Item item) {
		return (Long) getSession().createQuery("select count(*) from Item where productid LIKE :productid")
				.setParameter("productid", "%" + item.getProductId() + "%").uniqueResult();
	}

	public void persist(Item item) {
		getSession().save(item);
	}

	public User get(String productid) {
		return (User) getSession().createQuery("FROM Item WHERE productid=:productid").setParameter("productid", "%" + productid + "%").getSingleResult();
	}

}

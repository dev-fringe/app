package dev.fringe.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.fringe.entity.User;
import dev.fringe.support.AbstractRepository;

/**
 * @author dev-frine
 */
@Repository
@Transactional
public class UserRepository extends AbstractRepository<User, Long> {

	public void save() {
		for (int itr = 1; itr <= 100; itr++) {
			getSession().save(new User("sdsd" + itr));
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> list(Integer offset, Integer maxResults) {
		return getSession().createQuery("From User").setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : 10).list();
	}

	public int count() {
		return getSession().createQuery("From User").list().size();
	}

}

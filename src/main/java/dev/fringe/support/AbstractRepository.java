package dev.fringe.support;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author dev-frine
 */
@Repository
@Transactional
public abstract class AbstractRepository<T, PK extends Serializable> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractRepository() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("deprecation")
	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}

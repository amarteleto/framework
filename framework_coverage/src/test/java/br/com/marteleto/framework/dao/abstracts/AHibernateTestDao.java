package br.com.marteleto.framework.dao.abstracts;

import javax.persistence.EntityManager;

import br.com.marteleto.framework.persistence.hibernate.dao.abstracts.AHibernateDao;

public abstract class AHibernateTestDao extends AHibernateDao {
	private static final long serialVersionUID = 1L;
	
	public AHibernateTestDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}

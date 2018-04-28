package br.com.marteleto.framework.persistence.hibernate.dao.abstracts;

import org.hibernate.Session;

import br.com.marteleto.framework.persistence.dao.abstracts.ADao;

public abstract class AHibernateDao extends ADao {
	private static final long serialVersionUID = 1L;
	
	protected Session getSession() {
		return this.getEntityManager().unwrap(Session.class);
	}
}
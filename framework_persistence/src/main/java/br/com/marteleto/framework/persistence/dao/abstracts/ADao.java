package br.com.marteleto.framework.persistence.dao.abstracts;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.marteleto.framework.persistence.business.abstracts.ABusiness;
import br.com.marteleto.framework.persistence.dao.interfaces.IDao;

public abstract class ADao implements IDao {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(ABusiness.class.getName());
	@Inject protected EntityManager entityManager;
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
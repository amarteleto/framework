package br.com.marteleto.framework.business;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.marteleto.framework.business.abstracts.ATestHibernateBusiness;
import br.com.marteleto.framework.business.interfaces.ITestEntityPersistenceHibernateBusiness;
import br.com.marteleto.framework.dao.TestEntityPersistenceHibernateDao;
import br.com.marteleto.framework.dao.interfaces.ITestEntityPersistenceHibernateDao;
import br.com.marteleto.framework.filter.TestEntityPersistenceHibernateFilter;
import br.com.marteleto.framework.persistence.exception.BusinessException;
import br.com.marteleto.framework.vo.TestEntityPersistenceHibernate;
import br.com.marteleto.framework.vo.TestEntityPersistenceHibernateChild;

public class TestEntityPersistenceHibernateBusiness extends ATestHibernateBusiness<TestEntityPersistenceHibernate> implements ITestEntityPersistenceHibernateBusiness {
	private static final long serialVersionUID = 1L;
	private final ITestEntityPersistenceHibernateDao dao;
	
	public TestEntityPersistenceHibernateBusiness(EntityManager entityManager) {
		super(entityManager);
		this.dao = new TestEntityPersistenceHibernateDao(entityManager);
	}

	@Override
	public List<TestEntityPersistenceHibernate> listTestEntityPersistenceByFilter(TestEntityPersistenceHibernateFilter filter) throws BusinessException {
		try {
			return this.dao.listTestEntityPersistenceByFilter(filter);
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}

	@Override
	public List<TestEntityPersistenceHibernateChild> listTestEntityPersistenceChild() throws BusinessException {
		try {
			return this.dao.listTestEntityPersistenceChild();
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}
}
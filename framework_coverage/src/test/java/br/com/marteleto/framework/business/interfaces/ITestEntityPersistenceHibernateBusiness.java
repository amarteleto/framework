package br.com.marteleto.framework.business.interfaces;

import java.util.List;

import br.com.marteleto.framework.filter.TestEntityPersistenceHibernateFilter;
import br.com.marteleto.framework.persistence.business.interfaces.IBusiness;
import br.com.marteleto.framework.persistence.exception.BusinessException;
import br.com.marteleto.framework.vo.TestEntityPersistenceHibernate;
import br.com.marteleto.framework.vo.TestEntityPersistenceHibernateChild;

public interface ITestEntityPersistenceHibernateBusiness extends IBusiness<TestEntityPersistenceHibernate> {
	List<TestEntityPersistenceHibernate> listTestEntityPersistenceByFilter(TestEntityPersistenceHibernateFilter filter) throws BusinessException;
	List<TestEntityPersistenceHibernateChild> listTestEntityPersistenceChild() throws BusinessException;
}
package br.com.marteleto.framework.dao.interfaces;

import java.util.List;

import br.com.marteleto.framework.filter.TestEntityPersistenceHibernateFilter;
import br.com.marteleto.framework.persistence.dao.interfaces.IDao;
import br.com.marteleto.framework.persistence.exception.DaoException;
import br.com.marteleto.framework.vo.TestEntityPersistenceHibernate;
import br.com.marteleto.framework.vo.TestEntityPersistenceHibernateChild;

public interface ITestEntityPersistenceHibernateDao extends IDao {
	List<TestEntityPersistenceHibernate> listTestEntityPersistenceByFilter(TestEntityPersistenceHibernateFilter filter) throws DaoException;
	List<TestEntityPersistenceHibernateChild> listTestEntityPersistenceChild() throws DaoException;
}
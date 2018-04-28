package br.com.marteleto.framework.coverage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.marteleto.framework.business.TestEntityPersistenceHibernateBusiness;
import br.com.marteleto.framework.business.interfaces.ITestEntityPersistenceHibernateBusiness;
import br.com.marteleto.framework.filter.TestEntityPersistenceHibernateFilter;
import br.com.marteleto.framework.persistence.exception.BusinessException;
import br.com.marteleto.framework.persistence.exception.DaoException;
import br.com.marteleto.framework.persistence.producer.EntityManagerProducer;
import br.com.marteleto.framework.persistence.util.PesistenceMessageCode;
import br.com.marteleto.framework.vo.TestEntityPersistenceHibernate;
import br.com.marteleto.framework.vo.TestEntityPersistenceHibernateChild;

@SuppressWarnings("unused")
public class FrameworkPersistenceCoverage {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static ITestEntityPersistenceHibernateBusiness testEntityPersistenceBusiness; 
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit-hibernate");
		entityManager = entityManagerFactory.createEntityManager();
		testEntityPersistenceBusiness = new TestEntityPersistenceHibernateBusiness(entityManager);
	}

	@AfterClass
	public static void afterClass() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@Test
	public void testListEntityByFilter() throws BusinessException {
		Integer qtd = 1;
		TestEntityPersistenceHibernateFilter filter = new TestEntityPersistenceHibernateFilter();
		filter.setText("4");
		filter.setPageSize(10);
		filter.setFirst(0);
		filter.setSortField("TEEP.NM_TEEP_NAME");
		filter.setSortOrder("asc");
		List<TestEntityPersistenceHibernate> entities = testEntityPersistenceBusiness.listTestEntityPersistenceByFilter(filter);
		assertNotNull(entities);
		Integer count = entities.size();
		assertEquals(count, qtd);
	}
	
	@Test
	public void testListEntityChild() throws BusinessException {
		Integer qtd = 5;
		List<TestEntityPersistenceHibernateChild> entities = testEntityPersistenceBusiness.listTestEntityPersistenceChild();
		assertNotNull(entities);
		Integer count = entities.size();
		assertEquals(count, qtd);
	}
	
	@Test
	public void testPesistenceMessageCode() throws Exception {
		PesistenceMessageCode pesistenceMessageCode = new PesistenceMessageCode();
		String temp = null;
		temp = PesistenceMessageCode.PERSISTENCE_MESSAGE_0001;
		temp = PesistenceMessageCode.BUSINESS_MESSAGE_0001;
	}
	
	@Test
	public void testTestEntityPersistence() throws Exception {
		TestEntityPersistenceHibernate value1 = null;
		TestEntityPersistenceHibernate value2 = new TestEntityPersistenceHibernate();
		value2.setId(1l);
		value2.toString();
		value2.hashCode();
		assertFalse(value2.equals(value1));
		assertFalse(value2.equals(new Object()));
		value1 = new TestEntityPersistenceHibernate();
		value1.toString();
		value1.hashCode();
		assertFalse(value1.equals(value2));
		assertFalse(value2.equals(value1));
		value1.setId(1l);
		assertTrue(value1.equals(value2));
		value1 = value2;
		assertTrue(value1.equals(value2));
	}
	
	@Test
	public void testTestEntityPersistenceFilter() throws Exception {
		TestEntityPersistenceHibernateFilter value1 = null;
		TestEntityPersistenceHibernateFilter value2 = new TestEntityPersistenceHibernateFilter();
		value2.setId(1l);
		value2.toString();
		value2.hashCode();
		assertFalse(value2.equals(value1));
		assertFalse(value2.equals(new Object()));
		value1 = new TestEntityPersistenceHibernateFilter();
		value1.toString();
		value1.hashCode();
		assertFalse(value1.equals(value2));
		assertFalse(value2.equals(value1));
		value1.setId(1l);
		assertTrue(value1.equals(value2));
		value1 = value2;
		assertTrue(value1.equals(value2));
	}
	
	@Test
	public void testException() throws Exception {
		IOException IOException = new IOException();
		DaoException daoException = new DaoException();
		daoException = new DaoException("test 2");
		daoException = new DaoException("test 2",IOException);
		daoException = new DaoException(IOException);
		IOException = new IOException("teste");
		daoException = new DaoException(IOException);
		assertNotNull(daoException.getError());
		assertNotNull("",daoException.getMessage());
		BusinessException businessException = new BusinessException();
		businessException = new BusinessException("test 2");
		businessException = new BusinessException("test 2",IOException);
		businessException = new BusinessException(IOException);
		assertNotNull(businessException.getError());
		assertNotNull(businessException.getMessage());
	}
	
	@Test
	public void testGetEntity() throws BusinessException {
		Long id = 1l;
		TestEntityPersistenceHibernate testEntityPersistence = testEntityPersistenceBusiness.getEntityById(id);
		assertNotNull(testEntityPersistence);
		assertEquals(testEntityPersistence.getId(), id);
	}
	
	@Test
	public void testListEntity() throws BusinessException {
		Integer qtd = 5;
		List<TestEntityPersistenceHibernate> entities = testEntityPersistenceBusiness.listEntities();
		assertNotNull(entities);
		Integer count = entities.size();
		assertEquals(count, qtd);
	}
	
	@Test
	public void testNewEntity() throws BusinessException {
		Long id = 7l;
		TestEntityPersistenceHibernate testEntityPersistence = new TestEntityPersistenceHibernate();
		testEntityPersistence.setName("JUnit Test " + id);
		testEntityPersistenceBusiness.save(testEntityPersistence);
		assertEquals(testEntityPersistence.getId(), id);
	}
	
	@Test
	public void testUpdateEntity() throws BusinessException {
		Long id = 1l;
		TestEntityPersistenceHibernate testEntityPersistence = testEntityPersistenceBusiness.getEntityById(id);
		testEntityPersistence.setName("JUnit Test Update " + id);
		testEntityPersistenceBusiness.save(testEntityPersistence);
		assertNotNull(testEntityPersistence);
	}
	
	@Test
	public void testDeleteEntity() throws BusinessException {
		Integer qtd1 = testEntityPersistenceBusiness.listEntities().size();
		Integer tot = qtd1 - 1;
		Long id = 2l;
		TestEntityPersistenceHibernate testEntityPersistence = new TestEntityPersistenceHibernate();
		testEntityPersistence.setId(id);
		testEntityPersistenceBusiness.delete(testEntityPersistence);
		Integer qtd2 = testEntityPersistenceBusiness.listEntities().size();
		assertEquals(qtd2, tot);
	}
	
	@Test(expected = BusinessException.class)
	public void testDeleteEntityException() throws BusinessException {
		Long id = 10l;
		TestEntityPersistenceHibernate testEntityPersistence = new TestEntityPersistenceHibernate();
		testEntityPersistence.setId(id);
		testEntityPersistence.setName("JUnit Test");
		testEntityPersistenceBusiness.delete(testEntityPersistence);
	}
	
	@Test(expected = BusinessException.class)
	public void testSaveEntityException() throws BusinessException {
		Long id = 25l;
		TestEntityPersistenceHibernate testEntityPersistence = new TestEntityPersistenceHibernate();
		testEntityPersistence.setName("TestEntityPersistence 3");
		testEntityPersistenceBusiness.save(testEntityPersistence);
	}
	
	@Test
	public void testEntityManagerProducer() throws BusinessException {
		String name = null;
		EntityManagerProducer entityManagerProducer1 = new EntityManagerProducer();
		entityManagerProducer1.getEntityManager(name);
		name = "";
		EntityManagerProducer entityManagerProducer2 = new EntityManagerProducer();
		entityManagerProducer2.getEntityManager(name);
		name = "persistence-unit-hibernate";
		EntityManagerProducer entityManagerProducer3 = new EntityManagerProducer();
		entityManagerProducer3.getEntityManager("");
		entityManagerProducer3.getEntityManager(name);
	}
}

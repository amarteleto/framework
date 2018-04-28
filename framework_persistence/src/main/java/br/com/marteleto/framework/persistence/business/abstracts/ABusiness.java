package br.com.marteleto.framework.persistence.business.abstracts;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.marteleto.framework.persistence.business.interfaces.IBusiness;
import br.com.marteleto.framework.persistence.exception.BusinessException;
import br.com.marteleto.framework.persistence.vo.interfaces.IPersistenceEntity;

@SuppressWarnings({"unchecked"})
public abstract class ABusiness<T extends IPersistenceEntity> implements IBusiness<T> {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(ABusiness.class.getName());
	private Class<T> persistentClass;
	
	@Inject protected EntityManager entityManager;

	public ABusiness() {  
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];  
    }
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public T getEntityById(Long id) {
        return this.getEntityManager().find(this.persistentClass, id);
    }
	
	@Override
	public List<T> listEntities() {
		return this.getEntityManager().createQuery("SELECT tmp FROM " + this.persistentClass.getSimpleName() + " tmp").getResultList();
    }
	
	@Override
	public void save(T object) throws BusinessException {
		try {
			this.beginTransaction();
			this.saveInTransaction(object);
			this.commitTransaction();
		} catch (Exception ex) {
			this.rollbackTransaction();
			throw new BusinessException(ex);
		}
	}
	
	@Override
	public void saveInTransaction(T object) throws BusinessException {
		try {
			if (object.getId() == null) {
				this.getEntityManager().persist(object);
			} else {
				this.getEntityManager().merge(object);
			}
			this.getEntityManager().flush();
			this.getEntityManager().clear();
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}
	
	@Override
	public void delete(T object) throws BusinessException {
		try {
			this.beginTransaction();
			this.deleteInTransaction(object);
			this.commitTransaction();
		} catch (Exception ex) {
			this.rollbackTransaction();
			throw new BusinessException(ex);
		}
	}
	
	@Override
	public void deleteInTransaction(T object) throws BusinessException {
		try {
			this.getEntityManager().remove(this.getEntityManager().getReference(object.getClass(), object.getId()));
			this.getEntityManager().flush();
			this.getEntityManager().clear();
		} catch (Exception ex) {
			throw new BusinessException(ex);
		}
	}

	@Override
	public void beginTransaction() {
		this.getEntityManager().getTransaction().begin();
	}
	
	@Override
	public void rollbackTransaction() throws BusinessException {
		this.getEntityManager().getTransaction().rollback();
	}
	
	@Override
	public void commitTransaction() throws BusinessException {
		this.getEntityManager().getTransaction().commit();
	}
}
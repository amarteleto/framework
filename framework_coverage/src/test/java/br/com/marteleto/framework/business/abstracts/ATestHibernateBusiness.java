package br.com.marteleto.framework.business.abstracts;

import javax.persistence.EntityManager;

import br.com.marteleto.framework.persistence.business.abstracts.ABusiness;
import br.com.marteleto.framework.persistence.vo.interfaces.IPersistenceEntity;

public abstract class ATestHibernateBusiness<T extends IPersistenceEntity> extends ABusiness<T> {
	private static final long serialVersionUID = 1L;
	
	public ATestHibernateBusiness(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}

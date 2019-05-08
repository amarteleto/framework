package br.com.marteleto.framework.persistence.vo.abstracts;

import javax.persistence.MappedSuperclass;

import br.com.marteleto.framework.core.vo.abstracts.AEntity;
import br.com.marteleto.framework.persistence.vo.interfaces.IPersistenceEntity;

@MappedSuperclass
public abstract class APersistenceEntity extends AEntity implements IPersistenceEntity {
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
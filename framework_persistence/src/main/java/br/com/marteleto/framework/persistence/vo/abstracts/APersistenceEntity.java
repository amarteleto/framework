package br.com.marteleto.framework.persistence.vo.abstracts;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import br.com.marteleto.framework.core.vo.abstracts.AEntity;
import br.com.marteleto.framework.persistence.vo.interfaces.IPersistenceEntity;

@MappedSuperclass
public abstract class APersistenceEntity extends AEntity implements IPersistenceEntity {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO, generator="SEQUENCE")
    @Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
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
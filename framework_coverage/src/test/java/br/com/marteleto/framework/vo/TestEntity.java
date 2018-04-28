package br.com.marteleto.framework.vo;

import br.com.marteleto.framework.core.vo.abstracts.AEntity;

public class TestEntity extends AEntity {
	private static final long serialVersionUID = 1L;
	private Long id;
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return this.id;
	}
}

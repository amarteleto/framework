package br.com.marteleto.framework.model;

import br.com.marteleto.framework.vo.TestEntity;
import br.com.marteleto.framework.web.model.abstracts.AModel;

public class TestModel extends AModel<TestEntity> {
	private static final long serialVersionUID = 1L;
	public TestModel() {
	}
	public TestModel(TestEntity entity) {
		super();
		this.setEntity(entity);
	}
}
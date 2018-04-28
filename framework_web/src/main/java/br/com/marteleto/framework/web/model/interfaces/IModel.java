package br.com.marteleto.framework.web.model.interfaces;

import br.com.marteleto.framework.core.vo.interfaces.IEntity;

public interface IModel<T> extends IEntity {
	T getEntity();
	void setEntity(Object object);
}
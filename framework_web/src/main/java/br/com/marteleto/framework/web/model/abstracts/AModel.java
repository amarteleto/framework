package br.com.marteleto.framework.web.model.abstracts;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.marteleto.framework.core.util.ObjectUtil;
import br.com.marteleto.framework.core.vo.abstracts.AEntity;
import br.com.marteleto.framework.web.filter.abstracts.AFilter;
import br.com.marteleto.framework.web.model.interfaces.IModel;

@SuppressWarnings("unchecked")
public abstract class AModel<T> extends AEntity implements IModel<T> {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(AFilter.class.getName());
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setEntity(Object entity) {
		this.setObject(entity);
	}
	
	public T getEntity() {
		try {
			Object objeto = checkNewInstance(null);
			ObjectUtil.copy(objeto, this);
			return (T) objeto;
		} catch (Exception ex) {
			logger.log(Level.SEVERE,this.getClass().getSimpleName() + ": getEntity problem.", ex);
		}
		return null;
	}
	
	private void setObject(Object object) {
		try {
			object = checkNewInstance(object);
			ObjectUtil.copy(this, object);
		} catch (Exception ex) {
			logger.log(Level.SEVERE,this.getClass().getSimpleName() + ": setObject problem.", ex);
		}
	}
	
	private Object checkNewInstance(Object object) throws InstantiationException, IllegalAccessException {
		if (object == null) {
			Type type = getClass().getGenericSuperclass();
	        ParameterizedType paramType = (ParameterizedType) type;
			Class<T> clazz = (Class<T>) paramType.getActualTypeArguments()[0];
			object = clazz.newInstance();
		}
		return object;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AModel<?> other = (AModel<?>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
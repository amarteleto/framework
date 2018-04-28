package br.com.marteleto.framework.persistence.hibernate.transformer;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.transform.ResultTransformer;

import br.com.marteleto.framework.core.util.ConverterUtil;
import br.com.marteleto.framework.core.util.ObjectUtil;

@SuppressWarnings({"rawtypes", "unchecked"})
public class HibernateResultTransformer implements ResultTransformer {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(HibernateResultTransformer.class.getName());
	private final Class resultClass;
	private transient Map<Object,Object> entities;
	private transient Map<String, String> alias;
	public static final String PERSISTENCE_RESULT_TRANSFORMER_ERROR_0001 = "PERSISTENCE_RESULT_TRANSFORMER_ERROR_0001";
	public static final String PERSISTENCE_RESULT_TRANSFORMER_ERROR_0002 = "PERSISTENCE_RESULT_TRANSFORMER_ERROR_0002";
	public static final String PERSISTENCE_RESULT_TRANSFORMER_ERROR_0003 = "PERSISTENCE_RESULT_TRANSFORMER_ERROR_0003";
	public static final String PERSISTENCE_RESULT_TRANSFORMER_ERROR_0004 = "PERSISTENCE_RESULT_TRANSFORMER_ERROR_0004";
	public static final String PERSISTENCE_RESULT_TRANSFORMER_ERROR_0005 = "PERSISTENCE_RESULT_TRANSFORMER_ERROR_0005";
	

	public HibernateResultTransformer(Class resultClass) {
		if (resultClass == null) {
			throw new IllegalArgumentException(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0001);
		}
		this.resultClass = resultClass;
		this.entities = new LinkedHashMap<>();
	}

	public static HibernateResultTransformer aliasToBean(Class target) {
		return new HibernateResultTransformer(target);
	}

	@Override
	public List transformList(List list) {
		return new ArrayList<>(entities.values());
	}
	
	private boolean isCollection(Class clazz) {
		return Collection.class.isAssignableFrom(clazz);
	}
	
	private Collection newInstanceCollection(Class clazz) {
		if (Set.class.isAssignableFrom(clazz)) {
			return new HashSet<>();
		}
		return new ArrayList<>();
	}

	@Override
	public Object transformTuple(Object[] values, String[] fields) {
		Object result = null;
		try {
			Field attribute = null;
			Class clazz = resultClass;
			Object id = this.getId(values, fields, null);
			result = entities.get(id);
			if (result == null) {
				result = clazz.newInstance();
				entities.put(id,result);
			}
			for (int cont = 0; cont < fields.length; cont++) {
				String field = fields[cont];
				if (this.alias != null && this.alias.containsKey(field.toLowerCase())) {
					field = this.alias.get(field.toLowerCase());
				}
				String[] labels = null;
				int pos = field.indexOf('.');
				if (pos != -1) {
					labels = field.split("\\.");
				} else {
					labels = new String[] { field };
				}
				Object resultTemp = result;
				StringBuilder concatLabel = new StringBuilder();
				String separador = "";
				if (values[cont] != null) {
					for (String label : labels) {
						concatLabel.append(separador);
						concatLabel.append(label);
						attribute = ObjectUtil.getFieldByName(resultTemp.getClass(), label);
						if (attribute != null) {
							attribute.setAccessible(true);
							Class returnType = attribute.getType();
							if (this.isCollection(returnType)) {
								Collection instance = (Collection) attribute.get(resultTemp);
								if (instance == null) {
									instance = this.newInstanceCollection(returnType);
									attribute.set(resultTemp, instance);
								}
								if (attribute.getGenericType() instanceof ParameterizedType) {
									ParameterizedType parameterizedType = (ParameterizedType) attribute.getGenericType();
									Type[] typeArguments = parameterizedType.getActualTypeArguments();
									if (typeArguments != null && typeArguments.length == 1) {
										resultTemp = findEntityById(instance,this.getId(values, fields, label));
										if (resultTemp == null) {
											resultTemp = ((Class) typeArguments[0]).newInstance();
											instance.add(resultTemp);
										}
									} else {
										throw new HibernateException(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0002);
									}
								} else {
									throw new HibernateException(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0002);
								}
							} else {
								if (attribute.getType().isEnum() || attribute.getType().isPrimitive() || attribute.getType().getName().startsWith("java.")) {
									Object value = values[cont];
									if (value instanceof Clob) {
										Clob clob = (Clob) value;
										value = clob.getSubString(1, (int) clob.length());
									}
									attribute.set(resultTemp, ConverterUtil.convert(value, attribute.getType()));
								} else {
									Object temp = attribute.get(resultTemp);
									if (temp == null) {
										temp = attribute.getType().newInstance();
									}
									attribute.set(resultTemp, temp);
									resultTemp = temp;
								}
							}
						} else {
							if (label != null) {
								if (!"ROWNUM_".equalsIgnoreCase(label.trim().toUpperCase())) {
									logger.log(Level.WARNING, "LABEL not exists: [ {0} ]", label);
								}
							} else {
								logger.log(Level.SEVERE, "LABEL not exists: [ {0} ]", label);
							}
						}
						separador = ".";
					}
				}
			}
		} catch (Exception ex) {
			throw new HibernateException(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0003, ex);
		}
		return result;
	}
	
	public void addAlias(String key, String value) {
		if (this.alias == null) {
			this.alias = new HashMap<>();
		}
		this.alias.put(key, value);
	}
	
	public void removeAlias(String key) {
		if (this.alias != null && !this.alias.isEmpty()) {
			this.alias.remove(key);
		}
	}

	public Map<String, String> getAlias() {
		return alias;
	}

	public void setAlias(Map<String, String> alias) {
		this.alias = alias;
	}
	
	private Long getId(Object[] values, String[] aliases, String sufixo) {
		if (sufixo == null) {
			sufixo = "";
		} else {
			sufixo = sufixo + ".";
		}
		for (int i = 0; i < aliases.length; i++) {
			String name = aliases[i];
			if (this.getAlias() != null && this.getAlias().containsKey(name)) {
				name = this.getAlias().get(name);
			}
			if (name.trim().equalsIgnoreCase(sufixo.trim() + "id")) {
				if (values[i] != null) {
					return ConverterUtil.convert(values[i],Long.class);
				}
				return null;
			}
		}
		throw new HibernateException(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0004);
	}

	private Object findEntityById(Collection objects, Long id) throws IllegalAccessException {
		if (objects != null && !objects.isEmpty() && id != null) {
			for (Object object : objects) {
				Field attribute = ObjectUtil.getFieldByName(object.getClass(), "id");
				if (attribute != null) {
					attribute.setAccessible(true);
					Long value = ConverterUtil.convert(attribute.get(object), Long.class);
					if (value != null && id.equals(value)) {
						return object;
					}
				} else {
					throw new HibernateException(PERSISTENCE_RESULT_TRANSFORMER_ERROR_0005);
				}
			}
		}
		return null;
	}
}
package br.com.marteleto.framework.core.util;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

@SuppressWarnings({"rawtypes"})
public class ObjectUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static void  copy(Object destinationObject, Object sourceObject) throws IllegalAccessException, InvocationTargetException {
		if (destinationObject != null && sourceObject != null) {
			BeanUtils.copyProperties(destinationObject,sourceObject);
		}
	}
	
	public static Field getFieldByName(Class clazz, String name) {
		if (clazz != null && name != null && !"".equals(name.trim())) {
			List<Field> fields = new ArrayList<>();
			Class temp = clazz;
			while (temp != null) {
				fields.addAll(Arrays.asList(temp.getDeclaredFields()));
				temp = temp.getSuperclass();
			}
			for (Field field : fields) {
				if (field.getName().equalsIgnoreCase(name)) {
					return field;
				}
			}
		}
		return null;
	}
	
	public static Method findMethod(Object object, String methodName, Class<?> returnType, Class<?> parameterType) throws ReflectiveOperationException {
		if (object != null && methodName != null && !"".equals(methodName.trim())) {
			Method[] methods = object.getClass().getMethods();
			for (Method method : methods) {
				if (methodName.equalsIgnoreCase(method.getName())) {
					if ( (returnType == null && parameterType == null) || 
						 (returnType != null && method.getReturnType().equals(returnType))
					) {
						return method;
					} else if (parameterType != null && object.getClass().getMethod(method.getName(),parameterType) != null) {
						return object.getClass().getMethod(method.getName(),parameterType);
					}
				}
			}
		}
		return null;
	}
	
	public static Method findMethodGet(Object object, String methodName) throws ReflectiveOperationException {
		return findMethod(object, "get" + methodName, null, null);
	}

	public static Method findMethodSet(Object object, String methodName) throws ReflectiveOperationException {
		return findMethod(object, "set" + methodName, null, null);
	}
	
	public static Method findMethodSet(Object object, String methodName, Class<?> clazz) throws ReflectiveOperationException {
		return findMethod(object, "set" + methodName, null ,clazz);
	}
	
	public static void setObjectValue(Object object, String methodName, Object value) throws ReflectiveOperationException {
		setObjectValue(object,methodName,null,value);
	}
	
	public static void setObjectValue(Object object, String methodName, Class<?> clazz, Object value) throws ReflectiveOperationException {
		if (object != null && methodName != null) {
			Method method = findMethodSet(object,methodName,clazz);
			if (method != null) {
				method.invoke(object, value);
			}
		}
	}
	
	public static Object getObjectValue(Object object, String methodName) throws ReflectiveOperationException {
		if (object != null && methodName != null) {
			Method method = findMethodGet(object,methodName);
			if (method != null) {
				return method.invoke(object);
			}
		}
		return null;
	}
}
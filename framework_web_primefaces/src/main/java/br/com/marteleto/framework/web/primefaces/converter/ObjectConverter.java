package br.com.marteleto.framework.web.primefaces.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.com.marteleto.framework.web.primefaces.converter.abstracts.AConverter;

@FacesConverter(ObjectConverter.COMPONENT_TYPE)
public class ObjectConverter extends AConverter {
	private static final long serialVersionUID = 1L;
	public static final String COMPONENT_TYPE = "framework.objectConverter";
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) { 
		if (value != null && !"".equals(value.trim())) {
            return component.getAttributes().get(value);
        }
        return null;
	} 

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) { 
		if (value != null) {
			String id = value.toString();
			component.getAttributes().put(id, value);
			return id;
		}
        return null;
	}
}
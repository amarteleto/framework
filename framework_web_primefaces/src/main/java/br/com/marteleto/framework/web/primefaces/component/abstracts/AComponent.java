package br.com.marteleto.framework.web.primefaces.component.abstracts;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

public abstract class AComponent extends UINamingContainer implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(this.getClass().getName());
	public static final String COMPONENT_FAMILY = UINamingContainer.COMPONENT_FAMILY;
	
	@Override
    public String getFamily() {
        return AComponent.COMPONENT_FAMILY;
    }
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		this.processComponent(context);
		super.encodeBegin(context);
	}

	public void processComponent(FacesContext context) {
		context.isPostback();
		logger.info(this.getId() + " processComponent");
	}
}

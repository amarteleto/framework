package br.com.marteleto.framework.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.context.PartialViewContext;
import javax.faces.render.RenderKit;

import org.mockito.Mockito;
import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

public class FacesContextMocker implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static void mockFacesContext() {
		Map<String, Object> session = new HashMap<String, Object>();
		Map<Object, Object> attributes = new HashMap<Object, Object>();
		List<FacesMessage> messages = new ArrayList<>();
		
		FacesContext facesContext = Mockito.mock(FacesContext.class);
		Faces.setContext(facesContext);
		
		ExternalContext externalContext = Mockito.mock(ExternalContext.class);
		Flash flash = Mockito.mock(Flash.class);
		ELContext elContext = Mockito.mock(ELContext.class);
		ELResolver elResolver = Mockito.mock(ELResolver.class);
		UIViewRoot uiViewRoot = Mockito.mock(UIViewRoot.class);
		RequestContext requestContext = Mockito.mock(RequestContext.class);
		Application application = Mockito.mock(Application.class);
		RenderKit renderKit = Mockito.mock(RenderKit.class);
		PartialViewContext partialViewContext = Mockito.mock(PartialViewContext.class);
		attributes.put(RequestContext.class.getName(), requestContext);
				
		Mockito.when(facesContext.getExternalContext()).thenReturn(externalContext);
	    Mockito.when(externalContext.getFlash()).thenReturn(flash);
	    Mockito.when(externalContext.getSessionMap()).thenReturn(session);
	    Mockito.when(facesContext.getMessages()).thenReturn(messages.iterator());
	    Mockito.when(facesContext.getELContext()).thenReturn(elContext);
	    Mockito.when(elContext.getELResolver()).thenReturn(elResolver);
	    Mockito.when(facesContext.getViewRoot()).thenReturn(uiViewRoot);
	    Mockito.when(uiViewRoot.getLocale()).thenReturn(new Locale("pt","BR"));
	    Mockito.when(facesContext.getApplication()).thenReturn(application);
	    Mockito.when(facesContext.getRenderKit()).thenReturn(renderKit);
	    Mockito.when(facesContext.getAttributes()).thenReturn(attributes);
	    Mockito.when(facesContext.getPartialViewContext()).thenReturn(partialViewContext);
	    	    
	}
}
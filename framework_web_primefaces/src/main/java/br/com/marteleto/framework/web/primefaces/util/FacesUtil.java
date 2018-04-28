package br.com.marteleto.framework.web.primefaces.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.com.marteleto.framework.core.util.ResourceUtil;

public class FacesUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	protected static final transient Logger LOG = Logger.getLogger(FacesUtil.class.getName());
	
	public static void removeAllMessages() {
		FacesContext context = FacesContext.getCurrentInstance();
		Iterator<FacesMessage> it = context.getMessages();
		while ( it.hasNext() ) {
		    it.next();
		    it.remove();
		}
	}
	
	public static String getResource(String key) {
		return FacesUtil.getResource(key, null);
	}
	
	public static String getResource(String key, String[] parameters) {
		return ResourceUtil.getResource(key, parameters);
		/*
		String message = key;
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String messageBundleName = facesContext.getApplication().getMessageBundle();
			Locale locale = facesContext.getViewRoot().getLocale();
			ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);
			message = bundle.getString(key);
			if (parameters != null  && parameters.length > 0) {
				int cont=0;
				for (String parameter : parameters) {
					message = message.replace("{" + cont + "}", parameter);
					cont++;
				}
			}
		} catch (Exception ex) {
			LOG.debug(ex);
		}
		return message;
		*/
	}
	public static void showMessageWarn(String message) {
		FacesUtil.showMessageWarn(null,message);
	}
	
	public static void showMessageInfo(String message) {
		FacesUtil.showMessageInfo(null,message);
	}
	
	public static void showMessageError(String message) {
		FacesUtil.showMessageError(null,message);
	}
	
	public static void showMessageWarn(String title, String message) {
		FacesUtil.showMessageWarn(null,title,message,null);
	}
	
	public static void showMessageInfo(String title,String message) {
		FacesUtil.showMessageInfo(null,title,message,null);
	}
	
	public static void showMessageError(String title,String message) {
		FacesUtil.showMessageError(null,title,message,null);
	}
	
	public static void showMessageWarn(String title, String message, String[] parameters) {
		FacesUtil.showMessageWarn(null,title,message,parameters);
	}
	
	public static void showMessageInfo(String title,String message, String[] parameters) {
		FacesUtil.showMessageInfo(null,title,message,parameters);
	}
	
	public static void showMessageError(String title,String message, String[] parameters) {
		FacesUtil.showMessageError(null,title,message,parameters);
	}
	
	public static void showMessageWarn(String client,String title,String message) {
		FacesUtil.showMessage(FacesMessage.SEVERITY_WARN,client,title,message);
	}
	
	public static void showMessageInfo(String client,String title,String message) {
		FacesUtil.showMessage(FacesMessage.SEVERITY_INFO,client,title,message);
	}
	
	public static void showMessageError(String client,String title,String message) {
		FacesUtil.showMessage(FacesMessage.SEVERITY_ERROR,client,title,message);
	}
	
	public static void showMessageWarn(String client,String title,String message,String[] parameters) {
		FacesUtil.showMessage(FacesMessage.SEVERITY_WARN,client,title,message, parameters);
	}
	
	public static void showMessageInfo(String client,String title,String message, String[] parameters) {
		FacesUtil.showMessage(FacesMessage.SEVERITY_INFO,client,title,message, parameters);
	}
	
	public static void showMessageError(String client,String title,String message, String[] parameters) {
		FacesUtil.showMessage(FacesMessage.SEVERITY_ERROR,client,title,message, parameters);
	}
	
	public static void showMessage(Severity severidade,String client, String title,String message) {
		FacesUtil.showMessage(severidade, client, title, message, null);
	}
	
	public static void showMessage(Severity severidade,String client, String title,String message, String[] parameters) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
		facesContext.addMessage(client, new FacesMessage(severidade,FacesUtil.getResource(title),FacesUtil.getResource(message,parameters)));
	}
	
	public static Object getManagedBean(String beanName) {
	    Object bean = null;
	    try {
	        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	        bean = elContext.getELResolver().getValue(elContext, null, beanName);
	    } catch (RuntimeException ex) {
	        LOG.warning(ex.getMessage());
	    }
	    return bean;
	}
	
	public static void exceptionProcess(Exception ex) {
		FacesUtil.exceptionProcess(ex,null);
	}
	
	public static void exceptionProcess(String message) {
		FacesUtil.exceptionProcess(null,message);
	}
	
	public static void exceptionProcess(Exception ex, String message) {
		if (ex != null) {
			LOG.log(Level.SEVERE, message);
			if (ex.getMessage() != null && !"".equals(ex.getMessage().trim())) {
				message = ex.getMessage();
			}
		}
		if (message == null || "".equals(message.trim())) {
			message = "failed_not_mapping";
		}
		FacesUtil.showMessageError(null, message);
	}
}

package br.com.marteleto.framework.core.util;


import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ResourceUtil instance = null;
	protected static final transient Logger logger = Logger.getLogger(ResourceUtil.class.getName());
	private Locale locale;
	private transient List<java.util.ResourceBundle> resources;
	
	public ResourceUtil() {
		resources = new ArrayList<>();
		locale = new Locale("pt","BR");
	}
	
	private static ResourceUtil getInstance() {
		if (instance == null) {
			instance = new ResourceUtil();
		}
		return instance;
	}
	
	public static Locale getLocale() {
		return getInstance().locale;
	}

	public static void setLocale(Locale locale) {
		getInstance().locale = locale;
	}

	public static void addResource(String resourceName) {
    	getInstance().resources.add(ResourceBundle.getBundle(resourceName,getInstance().locale));
    }
    
    
	public static String getResource(String key) {
        return getInstance().getResource(getInstance().locale,key,null);
    }
    
    public static String getResource(String key, String[] parameters) {
        return getInstance().getResource(getInstance().locale,key,parameters);
    }
    public static String getResource(String key, String parameter) {
    	String[] parameters = {parameter};
        return getInstance().getResource(getInstance().locale,key,parameters);
    }
    
    public String getResource(Locale locale, String key, String[] parameters) {
    	String result = null;
    	if (resources != null && key != null && locale != null) {
    		try {
	    		for (java.util.ResourceBundle resource : resources) {
    				result = resource.getString(key);
	    			if (result != null && !result.trim().equals("")) {
	    				break;
	    			}
	    		}
	    		if (result != null && parameters != null) {
	            	result = MessageFormat.format(result, (Object[]) parameters);
	            }
    		} catch (RuntimeException ex) {
    			logger.log(Level.SEVERE, "Key fetch failed [ {0} ].", key); 
	        }
    	}
    	if (result == null) {
    		result = key;
    		logger.log(Level.SEVERE, "The key [ {0} ] was not found.", key);
		}
        return result;
    }
}

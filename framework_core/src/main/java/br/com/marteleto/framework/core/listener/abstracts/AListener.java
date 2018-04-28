package br.com.marteleto.framework.core.listener.abstracts;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.marteleto.framework.core.util.CoreMessageCode;
import br.com.marteleto.framework.core.util.PropertyUtil;
import br.com.marteleto.framework.core.util.ResourceUtil;

public abstract class AListener implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(AListener.class.getName());
	private static final String SPACER = "\n*************************************************";
	
	protected List<String> getProperties() {
		return Collections.emptyList();
	}
	
	protected List<String> getResources() {
		return Collections.emptyList();
	}
	
	protected void initListener() {
		initProperties();
		initResources();
		String[] appParameters = {PropertyUtil.getApplication(), PropertyUtil.getVersion()};
		String[] logParameters = {
				SPACER,
				"\n " + ResourceUtil.getResource(CoreMessageCode.CORE_MESSAGE_0001,appParameters).toUpperCase(),
				SPACER
		};
		logger.log(Level.INFO, "{0}{1}{2}", logParameters);
	}

	protected void finishListener() {
		String[] appParameters = {PropertyUtil.getApplication(), PropertyUtil.getVersion()};
		String[] logParameters = {
				SPACER,
				"\n " + ResourceUtil.getResource(CoreMessageCode.CORE_MESSAGE_0002,appParameters).toUpperCase(),
				SPACER
		};
		logger.log(Level.INFO, "{0}{1}{2}", logParameters);
	}
	
	protected void initProperties() {
		try {
			if (this.getProperties() != null && !this.getProperties().isEmpty()) {
				for (String property : this.getProperties()) {
					PropertyUtil.addProperty(property);
				}
			}
		} catch (IOException ex) {
			logger.severe("Failed to instantiate properties.");
		}
	}
	
	protected void initResources() {
		try {
			if (this.getResources() != null && !this.getResources().isEmpty()) {
				for (String resource : this.getResources()) {
					ResourceUtil.addResource(resource);
				}
			}
		} catch (RuntimeException ex) {
			logger.severe("Failed to instantiate resources.");
		}
	}
}
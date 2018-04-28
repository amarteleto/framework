package br.com.marteleto.framework.web.view.abstracts;

import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.marteleto.framework.web.filter.abstracts.AFilter;
import br.com.marteleto.framework.web.view.interfaces.IView;

public abstract class AView implements IView {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(AFilter.class.getName());
	
	public void preRenderView() {
		logger.log(Level.INFO,"{0} preRenderView",this.getViewId());
	}
}
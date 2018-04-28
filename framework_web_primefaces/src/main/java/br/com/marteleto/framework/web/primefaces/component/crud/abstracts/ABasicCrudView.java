package br.com.marteleto.framework.web.primefaces.component.crud.abstracts;

import java.util.logging.Level;

import br.com.marteleto.framework.web.primefaces.component.crud.interfaces.IBasicCrudView;
import br.com.marteleto.framework.web.view.abstracts.AView;

public abstract class ABasicCrudView extends AView implements IBasicCrudView {
	private static final long serialVersionUID = 1L;
	private static final String prefixLog = "[[[[[[";
	private static final String suffixLog = "]]]]]]";
	
	public boolean buttonRendered(String button) {
		return true;
	}
	
	public boolean buttonDisabled(String button) {
		return false;
	}
	
	public String getComponentLabel(String component) {
		return null;
	}
	
	private void logMessage(String name) {
		String[] message = {prefixLog,this.getViewId(),name,suffixLog};
		logger.log(Level.INFO,"{0} {1} {2} {3}", message);
	}
	
	public boolean btSaveActionListener() {
		this.logMessage("btSaveActionListener");
		return true;
	}
	
	public boolean btCancelActionListener() {
		this.logMessage("btCancelActionListener");
		return true;
	}
	
	public boolean btNewActionListener() {
		this.logMessage("btNewActionListener");
		return true;
	}
	
	public boolean btConsultActionListener() {
		this.logMessage("btConsultActionListener");
		return true;
	}
	
	public boolean btAlterActionListener() {
		this.logMessage("btAlterActionListener");
		return true;
	}
	
	public boolean btDeleteActionListener() {
		this.logMessage("btDeleteActionListener");
		return true;
	}
	
	public boolean btClearActionListener() {
		this.logMessage("btClearActionListener");
		return true;
	}
	
	public boolean btFilterActionListener(String text) {
		this.logMessage("btFilterActionListener");
		return true;
	}
	
	public boolean btActivateActionListener() {
		this.logMessage("btActivateActionListener");
		return true;
	}
	
	public boolean btInactivateActionListener() {
		this.logMessage("btInactivateActionListener");
		return true;
	}
	
	@Override
	public String btHomeAction() {
		this.logMessage("btHomeAction");
		return null;
	}
}
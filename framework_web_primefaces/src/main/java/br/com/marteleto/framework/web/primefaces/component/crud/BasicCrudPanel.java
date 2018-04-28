package br.com.marteleto.framework.web.primefaces.component.crud;

import javax.faces.component.FacesComponent;

import br.com.marteleto.framework.web.primefaces.component.crud.abstracts.ABasicCrud;

@FacesComponent(value=BasicCrudPanel.COMPONENT_TYPE)
public class BasicCrudPanel extends ABasicCrud {
	private static final long serialVersionUID = 1L;
	public static final String COMPONENT_TYPE = "framework.BasicCrudPanel";
	
	public boolean isMainPanelRendered() {
		return !this.isIndexMainDisabled();
	}
	
	public boolean isDetailPanelRendered() {
		return !this.isIndexDetailDisabled();
	}
}
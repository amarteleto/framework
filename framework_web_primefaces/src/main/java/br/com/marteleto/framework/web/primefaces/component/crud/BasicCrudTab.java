package br.com.marteleto.framework.web.primefaces.component.crud;

import javax.faces.component.FacesComponent;

import br.com.marteleto.framework.web.primefaces.component.crud.abstracts.ABasicCrud;

@FacesComponent(value=BasicCrudTab.COMPONENT_TYPE)
public class BasicCrudTab extends ABasicCrud {
	private static final long serialVersionUID = 1L;
	public static final String COMPONENT_TYPE = "framework.BasicCrudTab";
	public static final String BASIC_CRUD_TITLE_MAIN = "titleMain";
	public static final String BASIC_CRUD_TITLE_DETAIL = "titleDetail";
	
	public String getTitleMain() {
		return this.getComponentLabel(BASIC_CRUD_TITLE_MAIN);
	}
	
	public String getTitleDetail() {
		return this.getComponentLabel(BASIC_CRUD_TITLE_DETAIL);
	}
}
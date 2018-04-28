package br.com.marteleto.framework.web.primefaces.component.crud;

import javax.faces.component.FacesComponent;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;

import br.com.marteleto.framework.web.primefaces.component.crud.abstracts.ABasicCrud;

@FacesComponent(value=BasicCrudDialog.COMPONENT_TYPE)
public class BasicCrudDialog extends ABasicCrud {
	private static final long serialVersionUID = 1L;
	public static final String COMPONENT_TYPE = "framework.BasicCrudDialog";
	public static final String DIALOG_VAR_NAME = "dialogVar";
	
	enum PropertyKeys {
		dialogOpened,
	}
	
	public String getDialogWidgetVar() {
		return DIALOG_VAR_NAME;
	}
	
	@Override
	public void btNewActionListener(ActionEvent event) {
		super.btNewActionListener(event);
		if (this.getActiveIndex().equals(BASIC_CRUD_INDEX_DETAIL)) {
			this.openDialog();
		}
	}
	
	@Override
	public void btConsultActionListener(ActionEvent event) {
		super.btConsultActionListener(event);
		if (this.getActiveIndex().equals(BASIC_CRUD_INDEX_DETAIL)) {
			this.openDialog();
		}
	}
	
	@Override
	public void btAlterActionListener(ActionEvent event) {
		super.btAlterActionListener(event);
		if (this.getActiveIndex().equals(BASIC_CRUD_INDEX_DETAIL)) {
			this.openDialog();
		}
	}
	
	@Override
	public void btSaveActionListener(ActionEvent event) {
		super.btSaveActionListener(event);
		if (this.getActiveIndex().equals(BASIC_CRUD_INDEX_MAIN)) { 
			this.closeDialog();
			PrimeFaces.current().ajax().update(this.getClientId());
		}
	}
	
	@Override
	public void btCancelActionListener(ActionEvent event) {
		super.btCancelActionListener(event);
		if (this.getActiveIndex().equals(BASIC_CRUD_INDEX_MAIN)) {
			this.closeDialog();
			PrimeFaces.current().ajax().update(this.getClientId());
		}
	}
	
	public boolean isDialogOpened() {
		return (Boolean) getStateHelper().eval(PropertyKeys.dialogOpened, false);
	}
	public void setDialogOpened(boolean dialogOpened) {
		getStateHelper().put(PropertyKeys.dialogOpened, dialogOpened);
	}
	
	public void openDialog() {
		PrimeFaces.current().executeScript("PF('" + this.getDialogWidgetVar() + "').show()");
		this.setDialogOpened(true);
	}
	
	public void closeDialog() {
		PrimeFaces.current().executeScript("PF('" + this.getDialogWidgetVar() + "').hide()");
		this.setDialogOpened(false);
	}
}
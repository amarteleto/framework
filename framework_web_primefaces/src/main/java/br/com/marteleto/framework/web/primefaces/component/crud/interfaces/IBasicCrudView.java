package br.com.marteleto.framework.web.primefaces.component.crud.interfaces;

import java.util.List;

import org.primefaces.model.LazyDataModel;

import br.com.marteleto.framework.web.model.interfaces.IModel;
import br.com.marteleto.framework.web.primefaces.helper.ColumnHelper;
import br.com.marteleto.framework.web.view.interfaces.IView;

@SuppressWarnings("rawtypes")
public interface IBasicCrudView extends IView {
	LazyDataModel getLazy();
	List<ColumnHelper> getColumns();
	
	IModel getModel();
	void setModel(IModel model);
	
	String btHomeAction();
	
	boolean btSaveActionListener();
	boolean btCancelActionListener();
	
	boolean btNewActionListener();
	boolean btConsultActionListener();
	boolean btAlterActionListener();
	boolean btDeleteActionListener();
	boolean btActivateActionListener();
	boolean btInactivateActionListener();
	
	boolean btClearActionListener();
	boolean btFilterActionListener(String text);
	
	boolean buttonRendered(String button);
	boolean buttonDisabled(String button);
	String getComponentLabel(String component);
}

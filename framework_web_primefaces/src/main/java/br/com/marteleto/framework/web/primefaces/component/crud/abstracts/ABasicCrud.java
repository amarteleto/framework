package br.com.marteleto.framework.web.primefaces.component.crud.abstracts;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.model.LazyDataModel;

import br.com.marteleto.framework.web.model.interfaces.IModel;
import br.com.marteleto.framework.web.primefaces.component.abstracts.AComponent;
import br.com.marteleto.framework.web.primefaces.component.crud.interfaces.IBasicCrudView;
import br.com.marteleto.framework.web.primefaces.helper.ColumnHelper;

@SuppressWarnings("rawtypes")
public abstract class ABasicCrud extends AComponent {
	private static final long serialVersionUID = 1L;
	
	public static final Integer BASIC_CRUD_INDEX_MAIN = 0;
	public static final Integer BASIC_CRUD_INDEX_DETAIL = 1;
	
	public static final String BASIC_CRUD_TITLE_PRINCIPAL = "titlePrincipal";
	
	public static final String BASIC_CRUD_HEADER_FILTER = "headerFilter";
	
	public static final String BASIC_CRUD_TEXT_DELETE = "textDelete";
	public static final String BASIC_CRUD_TEXT_PLACEHOLDER = "textPlaceholder";
	public static final String BASIC_CRUD_TEXT_EMPTY = "textEmpty";
	public static final String BASIC_CRUD_TEXT_TOTAL_RECORDS = "textTotalRecords";
	public static final String BASIC_CRUD_TEXT_CANCEL_ALTER = "textCancelAlter";
	public static final String BASIC_CRUD_TEXT_TOOLTIP_HOME = "textTooltipHome";
	public static final String BASIC_CRUD_TEXT_TOOLTIP_FILTER = "textTooltipFilter";
	public static final String BASIC_CRUD_TEXT_TOOLTIP_EXPORT_EXCEL = "textTooltipExportExcel";
		
	public static final String BASIC_CRUD_BT_FILTER = "btFilter";
	public static final String BASIC_CRUD_BT_CLEAR = "btClear";
	public static final String BASIC_CRUD_BT_NEW = "btNew";
	public static final String BASIC_CRUD_BT_CONSULT = "btConsult";
	public static final String BASIC_CRUD_BT_ALTER = "btAlter";
	public static final String BASIC_CRUD_BT_DELETE = "btDelete";
	public static final String BASIC_CRUD_BT_SAVE = "btSave";
	public static final String BASIC_CRUD_BT_CANCEL = "btCancel";
	public static final String BASIC_CRUD_BT_ACTIVATE = "btActivate";
	public static final String BASIC_CRUD_BT_INACTIVATE = "btInactivate";
	public static final String BASIC_CRUD_BT_EXPORT_EXCEL = "btExportExcel";
	public static final String BASIC_CRUD_BT_HOME = "btHome";
	
	public static final String FILTER_NAME = "filter";
	public static final String DETAIL_NAME = "detail";
	
	private static final String modeInit = "init";
	private static final String modeAlter = "alter";
	private static final String modeConsult = "consult";
	
	enum PropertyKeys {
		view,
		activeIndex,
		collapsedFilter,
		idFacetFilter,
		idFacetDetail,
		filterText,
		mode,
	}
	
	@Override
	public void processComponent(FacesContext context) {
		super.processComponent(context);
		if (!FacesContext.getCurrentInstance().isPostback()) {
			this.setIdFacetFilter(this.processFacetId(context, this.getFacetFilterName()));
			this.setIdFacetDetail(this.processFacetId(context, this.getFacetDetailName()));
			this.setFilterText(null);
			this.getView().btFilterActionListener(this.getFilterText());
		}
	}
	
	private String getFacetFilterName() {
		return FILTER_NAME;
	}
	
	private String getFacetDetailName() {
		return DETAIL_NAME;
	}
	
	private String processFacetId(FacesContext context, String name) {
		if (this.isFacetExist(name)) {
			UIComponent component = this.getFacet(name);
			if (component instanceof UIPanel) {
				UIComponent panelGroup = context.getApplication().createComponent(UIPanel.COMPONENT_TYPE);
				if (panelGroup != null) {
					panelGroup.getChildren().add(component);
					this.getFacets().put(name, panelGroup);
					component = panelGroup;
				}
			}
			return component.getClientId();
		}
		return null;
	}
	
	private boolean isFacetExist(String name) {
		if (this.getFacetCount() > 0) {
			UIComponent component = this.getFacet(name);
			if (component != null) {
				return true;
			}
		}
		return false;
	}

	public boolean isFacetFilterExist() {
		return this.isFacetExist(this.getFacetFilterName());
	}
	
	public boolean isFacetDetailExist() {
		return this.isFacetExist(this.getFacetDetailName());
	}

	public IBasicCrudView getView() {
		return (IBasicCrudView) getStateHelper().eval(PropertyKeys.view);
	}
	public void setView(IBasicCrudView view) {
		getStateHelper().put(PropertyKeys.view, view);
	}

	public boolean isCollapsedFilter() {
		return (Boolean) getStateHelper().eval(PropertyKeys.collapsedFilter,true);
	}

	public void setCollapsedFilter(boolean collapsedFilter) {
		getStateHelper().put(PropertyKeys.collapsedFilter, collapsedFilter);
	}

	public String getMode() {
		return (String) getStateHelper().eval(PropertyKeys.mode,modeInit);
	}
	public void setMode(String mode) {
		getStateHelper().put(PropertyKeys.mode, mode);
	}

	public String getFilterText() {
		return (String) getStateHelper().eval(PropertyKeys.filterText);
	}

	public void setFilterText(String filterText) {
		getStateHelper().put(PropertyKeys.filterText, filterText);
	}

	public String getLbTotalRecords() {
		return this.getComponentLabel(BASIC_CRUD_TEXT_TOTAL_RECORDS);
	}

	public String getLbFilterHeader() {
		return this.getComponentLabel(BASIC_CRUD_HEADER_FILTER);
	}
	
	public String getDataTableEmptyMessage() {
		return this.getComponentLabel(BASIC_CRUD_TEXT_EMPTY);
	}
	
	public String getFilterTextPlaceholder() {
		return this.getComponentLabel(BASIC_CRUD_TEXT_PLACEHOLDER);
	}

	public String getLbExclusionQuestion() {
		return this.getComponentLabel(BASIC_CRUD_TEXT_DELETE);
	}
	
	public String getLbCancelQuestion() {
		return this.getComponentLabel(BASIC_CRUD_TEXT_CANCEL_ALTER);
	}
	
	public String getTitle() {
		return this.getComponentLabel(BASIC_CRUD_TITLE_PRINCIPAL);
	}
	
	public String getLbBtFilter() {
		return this.getComponentLabel(BASIC_CRUD_BT_FILTER);
	}

	public String getLbBtClear() {
		return this.getComponentLabel(BASIC_CRUD_BT_CLEAR);
	}

	public String getLbBtNew() {
		return this.getComponentLabel(BASIC_CRUD_BT_NEW);
	}

	public String getLbBtConsult() {
		return this.getComponentLabel(BASIC_CRUD_BT_CONSULT);
	}
	
	public String getLbBtAlter() {
		return this.getComponentLabel(BASIC_CRUD_BT_ALTER);
	}

	public String getLbBtDelete() {
		return this.getComponentLabel(BASIC_CRUD_BT_DELETE);
	}

	public String getLbBtSave() {
		return this.getComponentLabel(BASIC_CRUD_BT_SAVE);
	}
	
	public String getLbBtCancel() {
		return this.getComponentLabel(BASIC_CRUD_BT_CANCEL);
	}
	
	public String getLbBtActivate() {
		return this.getComponentLabel(BASIC_CRUD_BT_ACTIVATE);
	}
	
	public String getLbBtInactivate() {
		return this.getComponentLabel(BASIC_CRUD_BT_INACTIVATE);
	}

	public String getIdFacetFilter() {
		return (String) getStateHelper().eval(PropertyKeys.idFacetFilter);
	}
	public void setIdFacetFilter(String idFacetFilter) {
		getStateHelper().put(PropertyKeys.idFacetFilter, idFacetFilter);
	}
	
	public String getIdFacetDetail() {
		return (String) getStateHelper().eval(PropertyKeys.idFacetDetail);
	}
	public void setIdFacetDetail(String idFacetDetail) {
		getStateHelper().put(PropertyKeys.idFacetDetail, idFacetDetail);
	}
	
	public Integer getActiveIndex() {
		return (Integer) getStateHelper().eval(PropertyKeys.activeIndex, BASIC_CRUD_INDEX_MAIN);
	}
	public void setActiveIndex(Integer activeIndex) {
		getStateHelper().put(PropertyKeys.activeIndex, activeIndex);
	}

	public String getComponentLabel(String component) {
		String label = this.getView().getComponentLabel(component);
		if (label != null && !"".equals(label.trim())) {
			return label;
		}
		return this.getResourceBundleMap().get(component);
	}
	
	public IModel getModel() {
		return this.getView().getModel();
	}
	
	public void setModel(IModel model) {
		this.getView().setModel(model);
	}
	
	public LazyDataModel getLazy() {
		return this.getView().getLazy();
	}
	
	public List<ColumnHelper> getColumns() {
		return this.getView().getColumns();
	}
	
	public void preRenderView(ComponentSystemEvent event) {
		this.getView().preRenderView();
	}
	
	private boolean indexDisabled(Integer index) {
		return (index != null && !index.equals(this.getActiveIndex()));
	}
	
	public boolean isIndexMainDisabled() {
		return this.indexDisabled(BASIC_CRUD_INDEX_MAIN);
	}
	
	public boolean isIndexDetailDisabled() {
		return this.indexDisabled(BASIC_CRUD_INDEX_DETAIL);
	}
	
	/***** BT DISABLED ******/
	public boolean isBtFilterDisabled() {
		return this.getView().buttonDisabled(BASIC_CRUD_BT_FILTER);
	}
	
	public boolean isBtClearDisabled() {
		return this.getView().buttonDisabled(BASIC_CRUD_BT_CLEAR);
	}
	
	public boolean isBtCancelDisabled() {
		return this.getView().buttonDisabled(BASIC_CRUD_BT_CANCEL);
	}
	
	public boolean isBtNewDisabled() {
		return this.getView().buttonDisabled(BASIC_CRUD_BT_NEW);
	}
	
	public boolean isBtSaveDisabled() {
		return this.getView().buttonDisabled(BASIC_CRUD_BT_SAVE);
	}
	
	public boolean isBtActivateDisabled() {
		if (isModelExists()) {
			return this.getView().buttonDisabled(BASIC_CRUD_BT_ACTIVATE);
		}
		return true;
	}
	
	public boolean isBtInactivateDisabled() {
		if (isModelExists()) {
			return this.getView().buttonDisabled(BASIC_CRUD_BT_INACTIVATE);
		}
		return true;
	}
	
	public boolean isBtDeleteDisabled() {
		if (isModelExists()) {
			return this.getView().buttonDisabled(BASIC_CRUD_BT_DELETE);
		}
		return true;
	}
	
	public boolean isBtDeleteDetailDisabled() {
		return isBtDeleteDisabled();
	}
	
	public boolean isBtConsultDisabled() {
		if (isModelExists()) {
			return this.getView().buttonDisabled(BASIC_CRUD_BT_CONSULT);
		}
		return true;
	}
	
	public boolean isBtAlterDisabled() {
		if (isModelExists()) {
			return this.getView().buttonDisabled(BASIC_CRUD_BT_ALTER);
		}
		return true;
	}
	
	public boolean isBtAlterDetailDisabled() {
		return this.isBtAlterDisabled();
	}
	
	/***** BT ACTION ******/
	public String btHomeAction() {
		return this.getView().btHomeAction();
	}
	
	/***** BT ACTION LISTENER ******/
	public void btSaveActionListener(ActionEvent event) {
		boolean result = this.getView().btSaveActionListener();
		if (result) {
			this.setActiveIndex(BASIC_CRUD_INDEX_MAIN);
			this.setMode(modeInit);
		}
	}
	
	public void btCancelActionListener(ActionEvent event) {
		boolean result = this.getView().btCancelActionListener();
		if (result) {
			this.setActiveIndex(BASIC_CRUD_INDEX_MAIN);
			this.setMode(modeInit);
		}
	}
	
	public void btActivateActionListener(ActionEvent event) {
		this.getView().btActivateActionListener();
	}
	
	public void btInactivateActionListener(ActionEvent event) {
		this.getView().btInactivateActionListener();
	}
	
	public void btConsultActionListener(ActionEvent event) {
		boolean result = this.getView().btConsultActionListener();
		if (result) {
			this.setActiveIndex(BASIC_CRUD_INDEX_DETAIL);
			this.setMode(modeConsult);
		}
	}
	
	public void btAlterActionListener(ActionEvent event) {
		boolean result = this.getView().btAlterActionListener();
		if (result) {
			this.setActiveIndex(BASIC_CRUD_INDEX_DETAIL);
			this.setMode(modeAlter);
		}
	}
	
	public void btAlterDetailActionListener(ActionEvent event) {
		this.btAlterActionListener(event);
	}
	
	public void btNewActionListener(ActionEvent event) {
		boolean result = this.getView().btNewActionListener();
		if (result) {
			this.setActiveIndex(BASIC_CRUD_INDEX_DETAIL);
			this.setMode(modeAlter);
		}
	}
	
	public void btDeleteActionListener(ActionEvent event) {
		boolean result = this.getView().btDeleteActionListener();
		if (result) {
			this.setActiveIndex(BASIC_CRUD_INDEX_MAIN);
			this.setMode(modeInit);
		}
	}
	
	public void btDeleteDetailActionListener(ActionEvent event) {
		this.btDeleteActionListener(event);
	}
	
	public void btClearActionListener(ActionEvent event) {
		this.setFilterText(null);
		this.getView().btClearActionListener();
	}
	
	public void btFilterActionListener(ActionEvent event) {
		this.setModel(null);
		this.getView().btFilterActionListener(this.getFilterText());
	}

	public void commandLinkActionListener(Object object) {
		logger.info(this.getId() + " commandLinkActionListener");
		this.setModel((IModel) object);
		this.btAlterDetailActionListener(null);
	}
	
	public boolean isBtSaveDCRendered() {
		return !this.isIndexDetailDisabled();
	}
	
	public boolean isBtFilterDCRendered() {
		return !this.isIndexMainDisabled();
	}
	
	/***** BT RENDERED ******/
	public boolean isBtNewRendered() {
		return this.getView().buttonRendered(BASIC_CRUD_BT_NEW) && this.isFacetDetailExist() ;
	}
	
	public boolean isBtConsultRendered() {
		return this.getView().buttonRendered(BASIC_CRUD_BT_CONSULT) && this.isFacetDetailExist();
	}
	
	public boolean isBtAlterRendered() {
		return this.getView().buttonRendered(BASIC_CRUD_BT_ALTER) && this.isFacetDetailExist();
	}
	
	public boolean isBtAlterDetailRendered() {
		if (this.getMode().equals(modeConsult) && isModelExists()) {
			return this.getView().buttonRendered(BASIC_CRUD_BT_ALTER) && this.isFacetDetailExist();
		}
		return false;
	}
	
	public boolean isBtDeleteRendered() {
		return this.getView().buttonRendered(BASIC_CRUD_BT_DELETE);
	}
	
	public boolean isBtDeleteDetailRendered() {
		if (this.getMode().equals(modeConsult) && isModelExists()) {
			return this.isBtDeleteRendered();
		}
		return false;
	}
	
	public boolean isBtSaveRendered() {
		if (this.getMode().equals(modeAlter)) {
			return this.getView().buttonRendered(BASIC_CRUD_BT_SAVE);
		}
		return false;
	}
	
	public boolean isBtActivateRendered() {
		return this.getView().buttonRendered(BASIC_CRUD_BT_ACTIVATE);
	}
	
	public boolean isBtInactivateRendered() {
		return this.getView().buttonRendered(BASIC_CRUD_BT_INACTIVATE);
	}
	
	public boolean isBtFilterRendered() {
		return this.getView().buttonRendered(BASIC_CRUD_BT_FILTER);
	}
	
	public boolean isBtClearRendered() {
		return this.getView().buttonRendered(BASIC_CRUD_BT_CLEAR);
	}
	
	public boolean isBtCancelRendered() {
		if (this.getMode().equals(modeConsult)) {
			return this.getView().buttonRendered(BASIC_CRUD_BT_CANCEL);
		}
		return false;
	}
	
	public boolean isBtCancelConfirmRendered() {
		return !this.isBtCancelRendered();	
	}
	
	public boolean isBtExportExcelRendered() {
		return this.getView().buttonRendered(BASIC_CRUD_BT_EXPORT_EXCEL);
	}
	
	public boolean isBtHomeRendered() {
		return this.getView().buttonRendered(BASIC_CRUD_BT_HOME);
	}
	
	private boolean isModelExists() {
		return (this.getModel() != null && this.getModel().getId() != null);
	}
		
	public String getTooltipHome() {
		return this.getComponentLabel(BASIC_CRUD_TEXT_TOOLTIP_HOME);
	}
	
	public String getTooltipExportExcel() {
		return this.getComponentLabel(BASIC_CRUD_TEXT_TOOLTIP_EXPORT_EXCEL);
	}
	
	public String getTooltipFilter() {
		return this.getComponentLabel(BASIC_CRUD_TEXT_TOOLTIP_FILTER);
	}
}
package br.com.marteleto.framework.coverage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.weld.context.ConversationContext;
import org.jboss.weld.context.http.Http;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.model.SortOrder;

import br.com.marteleto.framework.core.util.CoreMessageCode;
import br.com.marteleto.framework.core.util.ResourceUtil;
import br.com.marteleto.framework.filter.TestWebFilter;
import br.com.marteleto.framework.form.TestForm;
import br.com.marteleto.framework.lazy.TestLazy;
import br.com.marteleto.framework.listener.TestServletContextListener;
import br.com.marteleto.framework.model.TestModel;
import br.com.marteleto.framework.util.FacesContextMocker;
import br.com.marteleto.framework.view.TestView;
import br.com.marteleto.framework.web.exception.WebException;
import br.com.marteleto.framework.web.primefaces.component.crud.BasicCrudDialog;
import br.com.marteleto.framework.web.primefaces.component.crud.BasicCrudPanel;
import br.com.marteleto.framework.web.primefaces.component.crud.BasicCrudTab;
import br.com.marteleto.framework.web.primefaces.converter.ObjectConverter;
import br.com.marteleto.framework.web.primefaces.util.FacesUtil;
import br.com.marteleto.framework.web.util.WebMessageCode;

@RunWith(CdiRunner.class)
@SuppressWarnings({"unused"})
public class FrameworkWebCoverage implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(FrameworkWebCoverage.class.getName());
	@Inject private TestView testView;
	
	
	@Produces @Http ConversationContext conversationContext;
	
	@Before
	public void setup() {
		FacesContextMocker.mockFacesContext();
		ResourceUtil.addResource("messages");
	}
	
	@Test
	public void testView() throws Exception {
		testView.preRenderView();
	}
	
	@Test
	public void testModel() throws Exception {
		TestModel model1 = new TestModel(null);
		TestModel model2 = new TestModel();
		model2.equals(model1);
		model1.setId(1L);
		model2.equals(model1);
		model1.getId();
		model1.hashCode();
		model1.getEntity();
		model1.toString();
		model2.equals(model1);
		model2.hashCode();
		model2.setEntity(model1);
		model2.equals(model2);
		model2.equals(model1);
		model2.equals(null);
		model2.setId(2L);
		model2.equals(model1);
	}
	
	@Test
	public void testFilter() throws Exception {
		TestWebFilter filter = new TestWebFilter();
		filter.doFilter(null, null, null);
	}
	
	@Test
	public void testListener() throws Exception {
		TestServletContextListener listener = new TestServletContextListener();
		listener.contextInitialized(null);
		listener.contextDestroyed(null);
	}
	
	@Test
	public void testException() throws Exception {
		IOException iox = new IOException();
		WebException ex = new WebException();
		ex = new WebException("test");
		ex = new WebException(iox);
		ex = new WebException("test",iox);
		ex.getClass();
	}
	
	@Test
	public void testWebMessageCode() throws Exception {
		WebMessageCode webMessageCode = new WebMessageCode();
		String temp = WebMessageCode.WEB_MESSAGE_0001;
	}
	
	
	@Test
	public void testFacesUtil() throws Exception {
		WebException exception = new WebException("test1");
		FacesUtil.removeAllMessages();
		String msg = FacesUtil.getResource(CoreMessageCode.CORE_MESSAGE_0001);
		assertTrue(msg.equals("{0} - {1} Successfully initialized."));
		String[] parameters = {"test1","test2"};
		msg = FacesUtil.getResource(CoreMessageCode.CORE_MESSAGE_0001,parameters);
		assertTrue(msg.equals("test1 - test2 Successfully initialized."));
		msg = null;
		FacesUtil.showMessageError("test");
		FacesUtil.showMessageInfo("test");
		FacesUtil.showMessageWarn("test");
		FacesUtil.showMessage(FacesMessage.SEVERITY_ERROR,"test","test","test");
		FacesUtil.showMessageError("test","test","test");
		FacesUtil.showMessageInfo("test","test","test");
		FacesUtil.showMessageWarn("test","test","test");
		FacesUtil.showMessageWarn("test","test",parameters);
		FacesUtil.showMessageInfo("test","test",parameters);
		FacesUtil.showMessageError("test","test",parameters);
		FacesUtil.exceptionProcess("test1");
		FacesUtil.exceptionProcess(msg);
		FacesUtil.exceptionProcess(exception);
		FacesUtil.exceptionProcess(exception,"test1");
		FacesUtil.getManagedBean("testView");
		FacesUtil.removeAllMessages();
	}
	
	@Test
	public void testLazy() throws Exception {
		TestModel model = new TestModel();
		model.setId(1l);
		TestLazy lazy = new TestLazy();
		lazy.load(0, 10, null, null, null);
		lazy.load(0, 10, null, SortOrder.ASCENDING, null);
		lazy.load(0, 10, null, SortOrder.DESCENDING, null);
		lazy.load(0, 10, null, null);
		lazy.getRowsPerPage();
		lazy.getRowData(null);
		lazy.getRowData("");
		lazy.getRowData("1");
		lazy.getRowKey(model);
		lazy.getFilters();
		lazy.getMultiSortMeta();
	}
	
	@Test
	public void testBasicCrud() {
		UIPanel filter = new UIPanel();
		UIPanel detail = new UIPanel();
		TestModel model = new TestModel();
		model.setId(2l);
		String componentType = null;
		componentType = BasicCrudPanel.COMPONENT_TYPE;
		componentType = BasicCrudPanel.BASIC_CRUD_TITLE_PRINCIPAL;
		componentType = BasicCrudPanel.BASIC_CRUD_HEADER_FILTER;
		componentType = BasicCrudPanel.BASIC_CRUD_TEXT_DELETE;
		componentType = BasicCrudPanel.BASIC_CRUD_TEXT_PLACEHOLDER;
		componentType = BasicCrudPanel.BASIC_CRUD_TEXT_EMPTY;
		componentType = BasicCrudPanel.BASIC_CRUD_TEXT_TOTAL_RECORDS;
		componentType = BasicCrudPanel.BASIC_CRUD_TEXT_CANCEL_ALTER;
		componentType = BasicCrudPanel.BASIC_CRUD_TEXT_TOOLTIP_HOME;
		componentType = BasicCrudPanel.BASIC_CRUD_TEXT_TOOLTIP_FILTER;
		componentType = BasicCrudPanel.BASIC_CRUD_TEXT_TOOLTIP_EXPORT_EXCEL;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_FILTER;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_CLEAR;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_NEW;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_CONSULT;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_ALTER;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_DELETE;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_SAVE;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_CANCEL;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_ACTIVATE;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_INACTIVATE;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_EXPORT_EXCEL;
		componentType = BasicCrudPanel.BASIC_CRUD_BT_HOME;
		componentType = BasicCrudPanel.FILTER_NAME;
		componentType = BasicCrudPanel.DETAIL_NAME;
		
		BasicCrudPanel basicCrudPanel = new BasicCrudPanel();
		basicCrudPanel.getFamily();
		basicCrudPanel.setView(testView);
		basicCrudPanel.getFacets().put(BasicCrudPanel.FILTER_NAME, filter);
		basicCrudPanel.getFacets().put(BasicCrudPanel.DETAIL_NAME, detail);
		basicCrudPanel.isMainPanelRendered();
		basicCrudPanel.isDetailPanelRendered();
		basicCrudPanel.isFacetFilterExist();
		basicCrudPanel.isFacetDetailExist();
		basicCrudPanel.getView();
		basicCrudPanel.isCollapsedFilter();
		basicCrudPanel.setCollapsedFilter(true);
		basicCrudPanel.setMode("consult");
		basicCrudPanel.getMode();
		basicCrudPanel.setFilterText("test");
		basicCrudPanel.getFilterText();
		basicCrudPanel.getLbTotalRecords();
		basicCrudPanel.getLbFilterHeader();
		basicCrudPanel.getDataTableEmptyMessage();
		basicCrudPanel.getFilterTextPlaceholder();
		basicCrudPanel.getLbExclusionQuestion();
		basicCrudPanel.getLbCancelQuestion();
		basicCrudPanel.getTitle();
		basicCrudPanel.getLbBtFilter();
		basicCrudPanel.getLbBtClear();
		basicCrudPanel.getLbBtNew();
		basicCrudPanel.getLbBtConsult();
		basicCrudPanel.getLbBtAlter();
		basicCrudPanel.getLbBtDelete();
		basicCrudPanel.getLbBtSave();
		basicCrudPanel.getLbBtCancel();
		basicCrudPanel.getLbBtActivate();
		basicCrudPanel.getLbBtInactivate();
		basicCrudPanel.getIdFacetFilter();
		basicCrudPanel.setIdFacetFilter("test1");
		basicCrudPanel.getIdFacetDetail();
		basicCrudPanel.setIdFacetDetail("test2");
		basicCrudPanel.getActiveIndex();
		basicCrudPanel.setActiveIndex(BasicCrudPanel.BASIC_CRUD_INDEX_DETAIL);
		basicCrudPanel.isMainPanelRendered();
		basicCrudPanel.isDetailPanelRendered();
		basicCrudPanel.getComponentLabel("test1");
		basicCrudPanel.getModel();
		basicCrudPanel.setModel(model);
		basicCrudPanel.getLazy();
		basicCrudPanel.getColumns();
		basicCrudPanel.preRenderView(null);
		basicCrudPanel.isIndexMainDisabled();
		basicCrudPanel.isIndexDetailDisabled();
		basicCrudPanel.isBtFilterDisabled();
		basicCrudPanel.isBtClearDisabled();
		basicCrudPanel.isBtCancelDisabled();
		basicCrudPanel.isBtNewDisabled();
		basicCrudPanel.isBtSaveDisabled();
		basicCrudPanel.isBtActivateDisabled();
		basicCrudPanel.isBtInactivateDisabled();
		basicCrudPanel.isBtDeleteDisabled();
		basicCrudPanel.isBtDeleteDetailDisabled();
		basicCrudPanel.isBtConsultDisabled();
		basicCrudPanel.isBtAlterDisabled();
		basicCrudPanel.isBtAlterDetailDisabled();
		basicCrudPanel.btHomeAction();
		basicCrudPanel.btSaveActionListener(null);
		basicCrudPanel.btCancelActionListener(null);
		basicCrudPanel.btActivateActionListener(null);
		basicCrudPanel.btInactivateActionListener(null);
		basicCrudPanel.btConsultActionListener(null);
		basicCrudPanel.btAlterActionListener(null);
		basicCrudPanel.btAlterDetailActionListener(null);
		basicCrudPanel.btNewActionListener(null);
		basicCrudPanel.btDeleteActionListener(null);
		basicCrudPanel.btDeleteDetailActionListener(null);
		basicCrudPanel.btClearActionListener(null);
		basicCrudPanel.btFilterActionListener(null);
		basicCrudPanel.commandLinkActionListener(null);
		basicCrudPanel.isBtSaveDCRendered();
		basicCrudPanel.isBtFilterDCRendered();
		basicCrudPanel.isBtNewRendered();
		basicCrudPanel.isBtConsultRendered();
		basicCrudPanel.isBtAlterRendered();
		basicCrudPanel.isBtAlterDetailRendered();
		basicCrudPanel.isBtDeleteRendered();
		basicCrudPanel.isBtDeleteDetailRendered();
		basicCrudPanel.isBtSaveRendered();
		basicCrudPanel.isBtActivateRendered();
		basicCrudPanel.isBtInactivateRendered();
		basicCrudPanel.isBtFilterRendered();
		basicCrudPanel.isBtClearRendered();
		basicCrudPanel.isBtCancelRendered();
		basicCrudPanel.isBtCancelConfirmRendered();
		basicCrudPanel.isBtExportExcelRendered();
		basicCrudPanel.isBtHomeRendered();
		basicCrudPanel.getTooltipHome();
		basicCrudPanel.getTooltipExportExcel();
		basicCrudPanel.getTooltipFilter();
		
		componentType = BasicCrudTab.COMPONENT_TYPE;
		BasicCrudTab basicCrudTab = new BasicCrudTab();
		basicCrudTab.setView(testView);
		basicCrudTab.getTitleMain();
		basicCrudTab.getTitleDetail();
		
		componentType = BasicCrudDialog.COMPONENT_TYPE;
		BasicCrudDialog basicCrudDialog = new BasicCrudDialog();
		basicCrudDialog.setView(testView);
		basicCrudDialog.btNewActionListener(null);
		basicCrudDialog.btConsultActionListener(null);
		basicCrudDialog.btAlterActionListener(null);
		basicCrudDialog.btSaveActionListener(null);
		basicCrudDialog.btCancelActionListener(null);
		basicCrudDialog.setActiveIndex(BasicCrudDialog.BASIC_CRUD_INDEX_DETAIL);
		basicCrudDialog.btNewActionListener(null);
		basicCrudDialog.btConsultActionListener(null);
		basicCrudDialog.btAlterActionListener(null);
		basicCrudDialog.btSaveActionListener(null);
		basicCrudDialog.btCancelActionListener(null);
		basicCrudDialog.isDialogOpened();
		basicCrudDialog.setDialogOpened(false);
		basicCrudDialog.openDialog();
		basicCrudDialog.closeDialog();
		try {
			basicCrudPanel.encodeBegin(FacesContext.getCurrentInstance());
			basicCrudPanel.processComponent(FacesContext.getCurrentInstance());
		} catch (IOException ex) {
			fail("Falha ao executar [processComponent] em [testBasicCrud].");
		}
	}
	
	@Test
	public void testConverter() throws Exception {
		ObjectConverter converter = new ObjectConverter();
		CommandButton component = new CommandButton();
		converter.getAsString(FacesContext.getCurrentInstance(),component,null);
		converter.getAsString(FacesContext.getCurrentInstance(),component,"");
		converter.getAsString(FacesContext.getCurrentInstance(),component,"teste");
		converter.getAsObject(FacesContext.getCurrentInstance(),component,null);
		converter.getAsObject(FacesContext.getCurrentInstance(),component,"teste");
	}
	
	@Test
	public void testForm() throws Exception {
		TestForm form = new TestForm();
		form.getFamily();
		form.encodeBegin(FacesContext.getCurrentInstance());
		form.processComponent(FacesContext.getCurrentInstance());
	}
}

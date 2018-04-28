package br.com.marteleto.framework.view;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.marteleto.framework.lazy.TestLazy;
import br.com.marteleto.framework.model.TestModel;
import br.com.marteleto.framework.web.model.interfaces.IModel;
import br.com.marteleto.framework.web.primefaces.component.crud.abstracts.ABasicCrudView;
import br.com.marteleto.framework.web.primefaces.helper.ColumnHelper;

@Named
@SuppressWarnings("rawtypes")
public class TestView extends ABasicCrudView {
	private static final long serialVersionUID = 1L;
	@Inject private TestLazy lazy;
	@Inject private TestModel model;

	@Override
	public String getViewId() {
		return this.getClass().getSimpleName();
	}

	@Override
	public LazyDataModel<?> getLazy() {
		return lazy;
	}

	@Override
	public List<ColumnHelper> getColumns() {
		List<ColumnHelper> list = new ArrayList<>();
		ColumnHelper columnHelper = new ColumnHelper();
		columnHelper.setHeader("test");
		columnHelper.setProperty("test");
		columnHelper.setStyle("test");
		columnHelper.setStyleClass("test");
		columnHelper.setSort("test");
		columnHelper.setAbbreviate(1);
		columnHelper.setLink(false);
		columnHelper.getHeader();
		columnHelper.getProperty();
		columnHelper.getStyle();
		columnHelper.getStyleClass();
		columnHelper.getSort();
		columnHelper.getAbbreviate();
		columnHelper.isLink();
		list.add(columnHelper);
		return list;
	}

	@Override
	public IModel<?> getModel() {
		return model;
	}

	@Override
	public void setModel(IModel model) {
		this.model = (TestModel) model;
	}
}
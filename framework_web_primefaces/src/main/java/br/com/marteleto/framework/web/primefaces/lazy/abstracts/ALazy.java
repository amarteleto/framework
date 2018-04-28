package br.com.marteleto.framework.web.primefaces.lazy.abstracts;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.com.marteleto.framework.web.exception.WebException;
import br.com.marteleto.framework.web.model.interfaces.IModel;
import br.com.marteleto.framework.web.primefaces.util.FacesUtil;

@SuppressWarnings({"rawtypes"})
public abstract class ALazy<T extends IModel> extends LazyDataModel<T>{
	private static final long serialVersionUID = 1L;
	private transient Map<String, Object> filters;
	private List<SortMeta> multiSortMeta;
	
	@Override
	public Object getRowKey(T object) {
		return object.getId();
	}
	
	@Override
	public T getRowData(String rowKey) {
		if (rowKey != null && !"".equals(rowKey.trim())) {
			List<T> list = this.getWrappedData();
			if (list != null && !list.isEmpty()) {
				for (T model : list) {
					if ((model.getId() != null && rowKey.equals(model.getId().toString()))) {
						return model;
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		this.load(first, pageSize, null, null, filters, multiSortMeta);
		return this.getWrappedData();
	}
	
	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		this.load(first, pageSize, sortField, sortOrder, filters,null);
		return this.getWrappedData();
	}
	
	public Integer getRowsPerPage() {
		return Integer.valueOf(10);
	}
	
	public Map<String, Object> getFilters() {
		return filters;
	}

	public List<SortMeta> getMultiSortMeta() {
		return multiSortMeta;
	}

	private void load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters, List<SortMeta> multiSortMeta) {
		this.filters = filters;
		this.multiSortMeta = multiSortMeta;
		this.setWrappedData(null);
		this.setRowCount(0);
		try {
			if (this.filterValidade()) {
				String order = null;
				if (sortOrder != null) {
					if (sortOrder.equals(SortOrder.ASCENDING)) {
						order = "asc";
					} else if (sortOrder.equals(SortOrder.DESCENDING)) {
						order= "desc";
					}
				}
				this.setRowCount(this.getBusinessResultCount());
				if (first >= this.getRowCount()) {
					first = 0;
				}
				this.setWrappedData(this.getBusinessResultList(first, pageSize, sortField, order));
			}
		} catch (Exception ex) {
			FacesUtil.exceptionProcess(ex,"failed to process " + this.getLazyModelName());
		}
	}
	
	protected abstract String getLazyModelName();
	protected abstract int getBusinessResultCount() throws WebException;
	protected abstract List<T> getBusinessResultList(int first, int pageSize, String sortField, String sortOrder) throws WebException;
	protected abstract boolean filterValidade();
}
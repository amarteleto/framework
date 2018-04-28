package br.com.marteleto.framework.lazy;

import java.util.ArrayList;
import java.util.List;

import br.com.marteleto.framework.model.TestModel;
import br.com.marteleto.framework.web.exception.WebException;
import br.com.marteleto.framework.web.primefaces.lazy.abstracts.ALazy;

public class TestLazy extends ALazy<TestModel> {
	private static final long serialVersionUID = 1L;

	@Override
	protected String getLazyModelName() {
		return TestLazy.class.getSimpleName();
	}

	@Override
	protected int getBusinessResultCount() throws WebException {
		return 0;
	}

	@Override
	protected List<TestModel> getBusinessResultList(int first, int pageSize, String sortField, String sortOrder) throws WebException {
		List<TestModel> list = new ArrayList<>();
		TestModel model = new TestModel();
		model.setId(1l);
		list.add(model);
		return list;
	}

	@Override
	protected boolean filterValidade() {
		return true;
	}

}

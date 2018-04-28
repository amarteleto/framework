package br.com.marteleto.framework.service;

import br.com.marteleto.framework.core.exception.FrameworkException;
import br.com.marteleto.framework.service.service.abstracts.AService;

public class TestService extends AService {
	private static final long serialVersionUID = 1L;
	
	public boolean exceptionProcess() {
		FrameworkException ex = new FrameworkException("coverage test");
		this.exceptionProcess(ex);
		return true;
	}
}

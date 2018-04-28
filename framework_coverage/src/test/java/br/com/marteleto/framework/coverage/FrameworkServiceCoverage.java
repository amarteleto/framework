package br.com.marteleto.framework.coverage;

import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.logging.Logger;

import org.junit.Test;

import br.com.marteleto.framework.service.TestService;

public class FrameworkServiceCoverage implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(FrameworkServiceCoverage.class.getName());

	@Test
	public void testCoreMessageCode() throws Exception {
		TestService service = new TestService();
		boolean result = service.exceptionProcess();
		assertTrue(result);
	}

}

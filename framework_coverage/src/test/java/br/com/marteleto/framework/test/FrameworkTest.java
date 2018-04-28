package br.com.marteleto.framework.test;

import java.io.Serializable;
import java.util.logging.Logger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

import br.com.marteleto.framework.coverage.FrameworkCoreCoverage;
import br.com.marteleto.framework.coverage.FrameworkPersistenceCoverage;
import br.com.marteleto.framework.coverage.FrameworkServiceCoverage;
import br.com.marteleto.framework.coverage.FrameworkWebCoverage;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	FrameworkCoreCoverage.class,
	FrameworkPersistenceCoverage.class,
	FrameworkWebCoverage.class,
	FrameworkServiceCoverage.class,
})
public class FrameworkTest implements Serializable {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(FrameworkTest.class.getName());
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(FrameworkTest.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }
	}
}

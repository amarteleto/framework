package br.com.marteleto.framework.service.service.abstracts;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import br.com.marteleto.framework.service.service.interfaces.IService;

public abstract class AService implements IService {
	private static final long serialVersionUID = 1L;
	protected final transient Logger logger = Logger.getLogger(AService.class.getName());
	
	protected Response exceptionProcess(Exception ex) {
		logger.log(Level.SEVERE, ex.getMessage(), ex);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}

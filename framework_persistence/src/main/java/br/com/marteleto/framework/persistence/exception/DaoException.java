package br.com.marteleto.framework.persistence.exception;

import br.com.marteleto.framework.core.exception.FrameworkException;
import br.com.marteleto.framework.persistence.util.PesistenceMessageCode;

public class DaoException extends FrameworkException {
	private static final long serialVersionUID = 1L;
	
	public DaoException() {
        super(PesistenceMessageCode.PERSISTENCE_MESSAGE_0001);
    }
	
	public DaoException(String message) {
        super(message);
    }
    
	public DaoException(String message, Throwable cause) {
		super(message,cause);
    }

	public DaoException(Throwable cause) {
        super(cause);
    }
}
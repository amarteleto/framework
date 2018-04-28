package br.com.marteleto.framework.persistence.exception;

import br.com.marteleto.framework.core.exception.FrameworkException;
import br.com.marteleto.framework.persistence.util.PesistenceMessageCode;

public class BusinessException extends FrameworkException {
	private static final long serialVersionUID = 1L;
	
	public BusinessException() {
        super(PesistenceMessageCode.BUSINESS_MESSAGE_0001);
    }
	
	public BusinessException(String message) {
        super(message);
    }
    
	public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

	public BusinessException(Throwable cause) {
        super(cause);
    }
}
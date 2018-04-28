package br.com.marteleto.framework.core.exception;

import br.com.marteleto.framework.core.exception.abstracts.AException;
import br.com.marteleto.framework.core.util.CoreMessageCode;

public class FrameworkException extends AException {
	private static final long serialVersionUID = 1L;
	public FrameworkException() {
        super(CoreMessageCode.CORE_MESSAGE_0005);
    }
	
	public FrameworkException(String message) {
        super(message);
    }
    
	public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }

	public FrameworkException(Throwable cause) {
        super(cause);
    }
}

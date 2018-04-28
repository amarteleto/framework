package br.com.marteleto.framework.web.exception;

import br.com.marteleto.framework.core.exception.FrameworkException;
import br.com.marteleto.framework.web.util.WebMessageCode;

public class WebException extends FrameworkException {
	private static final long serialVersionUID = 1L;
	
	public WebException() {
        super(WebMessageCode.WEB_MESSAGE_0001);
    }
	
	public WebException(String message) {
        super(message);
    }
    
	public WebException(String message, Throwable cause) {
        super(message, cause);
    }

	public WebException(Throwable cause) {
        super(cause);
    }
}
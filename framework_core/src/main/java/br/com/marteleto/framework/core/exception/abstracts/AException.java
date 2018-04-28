package br.com.marteleto.framework.core.exception.abstracts;

public abstract class AException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public AException(String message) {
        super(message);
        processException(message,null);
    }
    
	public AException(String message, Throwable cause) {
		processException(message,cause);
    }

	public AException(Throwable cause) {
        super(cause);
        processException(null,cause);
    }
	
	public String getError() {
	    return this.getMessage();
	}
	
	@Override
	public String getMessage() {
	    return message;
	}
	
	private void processException(String message, Throwable cause) {
		if (message != null && !"".equals(message.trim())) {
			this.message = message;
		} else if (cause != null && cause.getMessage() != null && !"".equals(cause.getMessage().trim())) {
			this.message = cause.getMessage();
		}
	}
}

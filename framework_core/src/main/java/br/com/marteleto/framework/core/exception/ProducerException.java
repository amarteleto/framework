package br.com.marteleto.framework.core.exception;

public class ProducerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ProducerException() {
        super();
    }
	
	public ProducerException(String message) {
        super(message);
    }
    
	public ProducerException(String message, Throwable cause) {
		super(message,cause);
    }

	public ProducerException(Throwable cause) {
        super(cause);
    }
}

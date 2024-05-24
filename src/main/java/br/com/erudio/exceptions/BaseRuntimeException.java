package br.com.erudio.exceptions;

public abstract class BaseRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public BaseRuntimeException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public BaseRuntimeException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}

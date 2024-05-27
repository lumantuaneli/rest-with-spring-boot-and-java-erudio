package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResourceNotFoundException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
        // TODO Auto-generated constructor stub
    }

    public ResourceNotFoundException(String pMessage) {
        super(pMessage);
        // TODO Auto-generated constructor stub
    }

    public ResourceNotFoundException(Throwable pCause) {
        super(pCause);
        // TODO Auto-generated constructor stub
    }

}

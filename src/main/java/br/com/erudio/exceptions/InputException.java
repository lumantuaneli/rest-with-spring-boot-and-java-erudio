package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InputException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InputException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public InputException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public InputException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public InputException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}

package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MathValidationException extends BaseRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MathValidationException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MathValidationException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public MathValidationException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public MathValidationException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}

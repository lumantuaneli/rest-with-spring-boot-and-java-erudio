package br.com.erudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends BaseRuntimeException {
    public static final String MSG_DEFAULT = "It is not allowed to persist a null object.";
    private static final long serialVersionUID = 1L;

    public RequiredObjectIsNullException() {
        this(MSG_DEFAULT);
    }

    public RequiredObjectIsNullException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }

    public RequiredObjectIsNullException(String pMessage) {
        super(pMessage);
    }

    public RequiredObjectIsNullException(Throwable pCause) {
        this(MSG_DEFAULT, pCause);
    }

}

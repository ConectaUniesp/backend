package br.com.redeuniesp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException  {
    public FileNotFoundException(String ex) {
        super(ex);
    }
    private static final long serialVersionUID = 1L;

    public FileNotFoundException(String ex, Throwable cause) {

        super(ex, cause);
    }
}

package br.com.redeuniesp.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends RuntimeException  {
    public FileStorageException(String ex) {
        super(ex);
    }
    private static final long serialVersionUID = 1L;

    public FileStorageException(String ex, Throwable cause) {

        super(ex, cause);
    }
}

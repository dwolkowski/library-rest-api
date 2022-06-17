package pl.dsw.dwolkowski.api.metadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "book not avaiable")
public class BookNotAvailableException extends RuntimeException {
}
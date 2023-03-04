package tn.devteam.immonexus.Entities;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateValidationException extends RuntimeException{
    public DateValidationException(String message) {
        super(message);
    }
}

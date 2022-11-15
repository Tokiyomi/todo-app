/*package carolina.garma.todoapp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class TodoExceptionHandler extends ResponseEntityExceptionHandler {

    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    //@ExceptionHandler(HttpMessageNotReadableException.class)
    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        TodoErrorDetails errorDetails = new TodoErrorDetails(new Date(), ex.getMessage(),request.getDescription(false), status);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}*/

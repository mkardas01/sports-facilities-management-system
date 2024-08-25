package put.poznan.sport.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import put.poznan.sport.exception.model.ErrorObject;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            RuntimeException.class,
            DataIntegrityViolationException.class
    })
    public ResponseEntity<ErrorObject> handleException(Exception exception) {
        ErrorObject errorObject = new ErrorObject();
        HttpStatus status;

        if (exception instanceof DataIntegrityViolationException) {
            status = HttpStatus.CONFLICT;
            errorObject.setMessage("Wystąpił błąd zapisu do bazy danych.");
        } else {
            status = HttpStatus.BAD_REQUEST;
            errorObject.setMessage(exception.getMessage());
        }

        errorObject.setStatusCode(status.value());
        errorObject.setTimestamp(new Date());

        log.error("Exception occurred: ", exception);

        return new ResponseEntity<>(errorObject, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorObject> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorObject errorObject = new ErrorObject();

        FieldError error = ex.getBindingResult().getFieldErrors().get(0);

        errorObject.setMessage(error.getDefaultMessage());
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setTimestamp(new Date());

        log.warn("Validation error: {}", error.getDefaultMessage());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }
}

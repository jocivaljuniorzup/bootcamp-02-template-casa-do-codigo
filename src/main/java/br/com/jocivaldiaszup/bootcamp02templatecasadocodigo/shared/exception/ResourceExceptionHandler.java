package br.com.jocivaldiaszup.bootcamp02templatecasadocodigo.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Validation error", e.getMessage(), request.getRequestURI());

        for(FieldError x : e.getBindingResult().getFieldErrors()){
            err.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e, HttpServletRequest request){

        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Illegal argument exception", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

}

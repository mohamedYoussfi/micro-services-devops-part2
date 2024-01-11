package net.youssfi.accountservice.exeptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, List<String>>>  handleValidationErrors(ConstraintViolationException exception){
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        Map<String, List<String>> listMap=new HashMap<>();
        constraintViolations.forEach(cv->{
            List<String> fieldErrors = listMap.get(cv.getPropertyPath().toString());
            if(fieldErrors==null){
                listMap.put(cv.getPropertyPath().toString(),new ArrayList<>());
            }
            listMap.get(cv.getPropertyPath().toString()).add(cv.getMessage());
        });
        return ResponseEntity.badRequest().body(listMap);
    }
}

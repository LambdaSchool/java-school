package com.lambdaschool.schools.handelers;

import com.lambdaschool.schools.exceptions.ResourceNotFoundExeption;
import com.lambdaschool.schools.models.ErrorDetail;
import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    @Autowired
    private HelperFunctions helperFunctions;
       /*
        title
        status
        detail
        timestamp
        developerMessage
         */

    @ExceptionHandler(ResourceNotFoundExeption.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundExeption rnfe)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date());
        errorDetail.setTitle("Resource Not Found Bubba!");
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloper(rnfe.getClass().getName());
        errorDetail.setErrors(helperFunctions.getValidationErrors(rnfe));
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }


}

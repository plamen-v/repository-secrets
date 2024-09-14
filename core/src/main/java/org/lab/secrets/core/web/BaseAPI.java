package org.lab.secrets.core.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class BaseAPI {
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}

package com.drpicox.game.common.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalRestControllerExceptionHandler {

    @ExceptionHandler(GlobalRestException.class)
    public ResponseEntity badRequest(GlobalRestException exception) {
        var body = new HashMap<String,Object>();
        body.put("isError", Boolean.TRUE);
        body.put("message", exception.getMessage());
        return new ResponseEntity(
                body,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }
}

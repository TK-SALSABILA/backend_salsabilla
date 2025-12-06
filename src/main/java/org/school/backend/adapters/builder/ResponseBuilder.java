package org.school.backend.adapters.builder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseBuilder {
    ResponseEntity<Object> build(Object data, long timeStamp, HttpStatus status);
    ResponseEntity<Object> meta(HttpStatus status,String message);
}
package ru.lanit.test.validation;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface Validation {

    ResponseEntity<String> validation(Map<String,String> jsonMap);
}

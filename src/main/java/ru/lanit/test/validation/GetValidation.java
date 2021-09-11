package ru.lanit.test.validation;

import org.springframework.http.ResponseEntity;

public interface GetValidation<T> {
    ResponseEntity<T> validation(String id);

}

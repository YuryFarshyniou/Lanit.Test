package ru.lanit.test.utils.validation;

import org.springframework.http.ResponseEntity;

public interface GetValidation<T> {
    ResponseEntity<T> validation(String id);

}

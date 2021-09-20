package ru.lanit.test.validation;

import ru.lanit.test.exception.ValidationException;

import java.util.Map;

public interface PostValidation {

    void validation(Map<String, String> jsonMap) throws ValidationException;

}

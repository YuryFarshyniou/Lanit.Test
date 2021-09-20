package ru.lanit.test.validation;

import ru.lanit.test.exception.ValidationException;
import ru.lanit.test.model.Person;

public interface GetValidation<T> {
    void validation(String id) throws ValidationException;

    void checkPersonIsPresentInDb(Person person);
}

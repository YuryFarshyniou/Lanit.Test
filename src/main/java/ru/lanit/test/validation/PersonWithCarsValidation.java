package ru.lanit.test.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.lanit.test.exception.ValidationException;
import ru.lanit.test.model.Person;
import ru.lanit.test.model.PersonWithCars;

import javax.persistence.EntityNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PersonWithCarsValidation implements GetValidation<PersonWithCars> {
    private final Logger logger = LoggerFactory.getLogger(PersonWithCarsValidation.class);

    @Override
    public void validation(String id) throws ValidationException {
        validateId(id);
    }
    public void checkPersonIsPresentInDb(Person person) {
        if (person == null) {
            throw new EntityNotFoundException("Person with this id doesn't exists!");
        }
    }

    private void validateId(String id) throws ValidationException {
        checkStringIsEmpty(id);
        checkThisStringISaNumber(id);
    }

    private void checkStringIsEmpty(String id) throws ValidationException {
        if (id.isEmpty()) {
            logger.warn("Person's id is empty!");
            throw new ValidationException("Person's id is empty!");
        }
    }

    private void checkThisStringISaNumber(String row) throws ValidationException {
        Pattern pattern = Pattern.compile("^\\d*$");
        Matcher matcher = pattern.matcher(row);
        if (matcher.find()) {
            try {
                long number = Long.parseLong(matcher.group());
            } catch (NumberFormatException e) {
                logger.warn("Person's id is too big!");
                throw new ValidationException("Person's id is too big!");
            }
        } else {
            logger.warn("Person's id isn't a number!");
            throw new ValidationException("Person's id isn't a number!");
        }
    }
}
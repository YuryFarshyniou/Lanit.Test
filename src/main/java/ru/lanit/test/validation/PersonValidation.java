package ru.lanit.test.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.lanit.test.exception.ValidationException;
import ru.lanit.test.service.personService.IPersonService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("personValidator")
public class PersonValidation implements PostValidation {
    private final IPersonService personIService;
    Logger logger = LoggerFactory.getLogger(PersonValidation.class);

    public PersonValidation(IPersonService personIService) {
        this.personIService = personIService;
    }

    @Override
    public void validation(Map<String, String> jsonMap) throws ValidationException {
        validateId(jsonMap.get("id"));
        validateName(jsonMap.get("name"));
        validateDate(jsonMap.get("birthdate"));
    }

    private void isNotPresentPersonInDB(String id) throws ValidationException {
        Long personId = personIService.findPersonId(Long.parseLong(id));
        if (personId != null) {
            logger.warn("Person has already exists!");
            throw new ValidationException("Person with this id has already exist!");
        }
    }

    private void validateId(String id) throws ValidationException {
        checkStringIsEmpty(id);
        checkThisStringISaNumber(id);
        isNotPresentPersonInDB(id);
    }

    private void validateName(String name) throws ValidationException {
        if (name.isEmpty()) {
            throw new ValidationException("Person's name is empty!");
        }
    }

    private void validateDate(String date) throws ValidationException {
        checkStringIsEmpty(date);
        checkDateFormat(date);
    }

    private void checkStringIsEmpty(String id) throws ValidationException {
        if (id.isEmpty()) {
            throw new ValidationException("String is empty!");
        }
    }

    private void checkThisStringISaNumber(String row) throws ValidationException {
        Pattern pattern = Pattern.compile("^\\d*$");
        Matcher matcher = pattern.matcher(row);
        if (matcher.find()) {
            try {
                long number = Long.parseLong(matcher.group());
            } catch (NumberFormatException e) {
                logger.warn("This number is too big!");
                throw new ValidationException("This number is too big!");
            }
        } else {
            logger.warn("This string is not a number!");
            throw new ValidationException("This string is not a number!");
        }
    }

    private void checkDateFormat(String birthdate) throws ValidationException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date currentDate;
        try {
            currentDate = simpleDateFormat.parse(birthdate);
        } catch (ParseException e) {
            logger.warn("Birthdate doesn't match to format!");
            throw new ValidationException("Birthdate doesn't match to format!");
        }
        if (new Date().compareTo(currentDate) < 1) {
            throw new ValidationException("Birthdate is more than now!");
        }
    }
}

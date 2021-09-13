package ru.lanit.test.validation.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.lanit.test.create.CreateEntityForPostMethod;
import ru.lanit.test.model.person.Person;
import ru.lanit.test.service.personService.IPersonService;
import ru.lanit.test.validation.PostValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("personValidator")
public class PersonValidation implements PostValidation {
    private final IPersonService personIService;
    private final CreateEntityForPostMethod<Person> createPerson;
    Logger logger = LoggerFactory.getLogger(PersonValidation.class);

    public PersonValidation(IPersonService personIService,
                            @Qualifier("createPerson") CreateEntityForPostMethod<Person> createPerson) {
        this.personIService = personIService;
        this.createPerson = createPerson;
    }

    @Override
    public ResponseEntity<String> validation(Map<String, String> jsonMap) {
        List<Boolean> valid = validateAll(jsonMap);
        for (Boolean validation : valid) {
            if (!validation) {
                return new ResponseEntity<>("Validation problem", HttpStatus.BAD_REQUEST);
            }
        }
        Person person = createPerson.createEntity(jsonMap);
        personIService.save(person);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    private List<Boolean> validateAll(Map<String, String> jsonMap) {
        List<Boolean> allValidations = new ArrayList<>();
        allValidations.add(validateId(jsonMap.get("id")));
        allValidations.add(validateName(jsonMap.get("name")));
        allValidations.add(validateDate(jsonMap.get("birthdate")));
        allValidations.add(isNotPresentPersonInDB(jsonMap.get("id")));
        return allValidations;
    }

    private boolean isNotPresentPersonInDB(String id) {
        if (validateId(id)) {
            Long personId = personIService.findPersonId(Long.parseLong(id));
            if (personId == null) {
                return true;
            } else {
                logger.warn("Person with this id has already exist");
                return false;
            }
        }
        logger.warn("Wrong Id validation.");
        return false;
    }

    private boolean validateId(String id) {
        if (id.isEmpty()) {
            logger.warn("Person id is null!");
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d*$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) {
            try {
                long personId = Long.parseLong(matcher.group());
            } catch (NumberFormatException e) {
                logger.warn("Too big id number!");
                return false;
            }
            return true;
        } else {
            logger.warn("This id is not a number!");
            return false;
        }
    }

    private boolean validateName(String name) {
        if (!name.isEmpty()) {
            return true;
        } else {
            logger.warn("Person name is null");
            return false;
        }
    }

    private boolean validateDate(String date) {
        if (date.isEmpty()) {
            logger.warn("Birthdate is null!");
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date currentDate;
        try {
            currentDate = simpleDateFormat.parse(date);

        } catch (ParseException e) {
            logger.warn("Birthdate doesn't match to format!!");
            return false;
        }
        return new Date().compareTo(currentDate) > 0;
    }
}

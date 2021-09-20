package ru.lanit.test.controller;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lanit.test.exception.ValidationException;
import ru.lanit.test.model.Person;
import ru.lanit.test.service.personService.IPersonService;
import ru.lanit.test.validation.PostValidation;

import java.util.Map;

@RestController
public class PersonController {
    private final PostValidation personValidation;
    private final IPersonService personService;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public PersonController(@Qualifier("personValidator") PostValidation personValidation,
                            IPersonService personService) {
        this.personValidation = personValidation;
        this.personService = personService;

    }

    @PostMapping(value = "/person")
    public ResponseEntity<Person> savePerson(@RequestBody Map<String, String> personInformation) {
        personValidation.validation(personInformation);
        Person person = new Person(personInformation);
        personService.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Person validation ended with errors!")
    @ExceptionHandler
    public void handleExceptions(ValidationException e) {
        logger.warn("Person validation ended with errors!, error message: {}", e.getMessage());
    }
}

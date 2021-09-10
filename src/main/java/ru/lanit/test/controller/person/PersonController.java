package ru.lanit.test.controller.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.test.model.person.Person;
import ru.lanit.test.service.personService.IPersonService;
import ru.lanit.test.validation.Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
public class PersonController {
    private final IPersonService personService;
    private final Validation personValidation;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public PersonController(IPersonService personService,@Qualifier("personValidator") Validation personValidation) {
        this.personService = personService;
        this.personValidation = personValidation;
    }

    @PostMapping(value = "/person")
    public ResponseEntity<String> savePerson(@RequestBody Map<String, String> personInformation) {
        ResponseEntity<String> validation = personValidation.validation(personInformation);
        if (validation.getStatusCode().is2xxSuccessful()) {
            Person person = createPerson(personInformation);
            personService.save(person);
        }
        return validation;
    }

    private Person createPerson(Map<String, String> personInformation) {
        Person person1 = new Person();
        person1.setId(Long.parseLong(personInformation.get("id")));
        person1.setName(personInformation.get("name"));
        try {
            person1.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse(personInformation.get("birthdate")));
        } catch (ParseException e) {
            logger.warn("Wrong date format");
        }
        return person1;
    }
}

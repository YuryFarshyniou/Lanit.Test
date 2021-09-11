package ru.lanit.test.controller.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lanit.test.model.person.Person;
import ru.lanit.test.model.person_with_cars.PersonWithCars;
import ru.lanit.test.service.personService.IPersonService;
import ru.lanit.test.validation.GetValidation;
import ru.lanit.test.validation.PostValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
public class PersonController {
    private final IPersonService personService;
    private final PostValidation personValidation;
    private final GetValidation<PersonWithCars> personWithCarsValidation;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public PersonController(IPersonService personService,
                            @Qualifier("personValidator") PostValidation personValidation,
                            GetValidation<PersonWithCars> personWithCarsValidation) {
        this.personService = personService;
        this.personValidation = personValidation;
        this.personWithCarsValidation = personWithCarsValidation;
    }

    @GetMapping(value = "/personWithCars")
    public ResponseEntity<PersonWithCars> getPersonWithCars(@RequestParam(name = "personId") String id) {

        return personWithCarsValidation.validation(id);
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

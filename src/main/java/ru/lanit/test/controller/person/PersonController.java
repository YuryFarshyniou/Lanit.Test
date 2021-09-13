package ru.lanit.test.controller.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lanit.test.model.person_with_cars.PersonWithCars;
import ru.lanit.test.utils.validation.GetValidation;
import ru.lanit.test.utils.validation.PostValidation;

import java.util.Map;

@RestController
public class PersonController {
    private final PostValidation personValidation;
    private final GetValidation<PersonWithCars> personWithCarsValidation;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public PersonController(@Qualifier("personValidator") PostValidation personValidation,
                            GetValidation<PersonWithCars> personWithCarsValidation) {
        this.personValidation = personValidation;
        this.personWithCarsValidation = personWithCarsValidation;
    }

    @GetMapping(value = "/personWithCars")
    public ResponseEntity<PersonWithCars> getPersonWithCars(@RequestParam(name = "personId") String id) {
        return personWithCarsValidation.validation(id);
    }

    @PostMapping(value = "/person")
    public ResponseEntity<String> savePerson(@RequestBody Map<String, String> personInformation) {
        return personValidation.validation(personInformation);
    }


}

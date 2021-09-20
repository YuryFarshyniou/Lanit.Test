package ru.lanit.test.controller;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.lanit.test.exception.ValidationException;
import ru.lanit.test.model.Car;
import ru.lanit.test.model.Person;
import ru.lanit.test.model.PersonWithCars;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.service.personService.IPersonService;
import ru.lanit.test.validation.GetValidation;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class PersonWithCarsController {
    private final ICarService carService;
    private final GetValidation<PersonWithCars> personWithCarsValidation;
    private final IPersonService personService;
    private final Logger logger = LoggerFactory.getLogger(PersonWithCarsController.class);


    public PersonWithCarsController(ICarService carService,
                                    GetValidation<PersonWithCars> personWithCarsValidation,
                                    IPersonService personService) {
        this.carService = carService;
        this.personWithCarsValidation = personWithCarsValidation;
        this.personService = personService;
    }

    @GetMapping(value = "/personWithCars")
    public ResponseEntity<PersonWithCars> getPersonWithCars(@RequestParam(name = "personId") String id) {
        personWithCarsValidation.validation(id);
        long personID = Long.parseLong(id);
        Person person = personService.findPersonById(personID);
        personWithCarsValidation.checkPersonIsPresentInDb(person);
        List<Car> cars = carService.findAllByOwnerId(personID);
        PersonWithCars personWithCars = new PersonWithCars(person, cars);
        return new ResponseEntity<>(personWithCars, HttpStatus.OK);
    }



    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Person validation ended with errors!")
    @ExceptionHandler
    public void handleExceptions(ValidationException e) {
        logger.warn("Person validation ended with errors!, error message: {}", e.getMessage());
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Person with this id doesn't exists!")
    @ExceptionHandler
    public void handleExceptions(EntityNotFoundException e) {
        logger.warn("Person with this id doesn't exists!, error message: {}", e.getMessage());
    }

}

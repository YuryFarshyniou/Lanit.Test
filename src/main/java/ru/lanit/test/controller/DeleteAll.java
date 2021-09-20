package ru.lanit.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.service.personService.IPersonService;

@RestController
public class DeleteAll {
    private final IPersonService personService;
    private final ICarService carService;
    private final Logger logger = LoggerFactory.getLogger(DeleteAll.class);

    public DeleteAll(IPersonService personService, ICarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    @GetMapping(value = "/clear")
    public ResponseEntity deleteAll() {
        personService.deleteAll();
        carService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Something wrong with deleteMethod!")
    @ExceptionHandler
    public void handleExceptions(Exception e) {
        logger.warn("Problem with delete operation!");
    }
}

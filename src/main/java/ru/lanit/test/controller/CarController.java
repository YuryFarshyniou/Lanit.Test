package ru.lanit.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lanit.test.exception.ValidationException;
import ru.lanit.test.model.Car;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.validation.PostValidation;

import java.util.Map;

@RestController
public class CarController {
    private final PostValidation carValidation;
    private final ICarService carService;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public CarController(@Qualifier("carValidation") PostValidation carValidation,
                         ICarService carService) {
        this.carValidation = carValidation;
        this.carService = carService;
    }

    @PostMapping(value = "/car")
    public ResponseEntity<Car> saveCar(@RequestBody Map<String, String> carInformation) {
        carValidation.validation(carInformation);
        Car car = new Car(carInformation);
        carService.save(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Car validation ended with errors!")
    @ExceptionHandler
    public void handleExceptions(ValidationException e) {
        logger.warn("Car validation ended with errors!, error message: {}", e.getMessage());
    }
}

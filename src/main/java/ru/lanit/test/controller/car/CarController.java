package ru.lanit.test.controller.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.test.controller.person.PersonController;
import ru.lanit.test.utils.validation.PostValidation;

import java.util.Map;

@RestController
public class CarController {
    private final PostValidation carValidation;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public CarController(@Qualifier("carValidation") PostValidation carValidation) {
        this.carValidation = carValidation;
    }

    @PostMapping(value = "/car")
    public ResponseEntity<String> saveCar(@RequestBody Map<String, String> carInformation) {
        return carValidation.validation(carInformation);
    }
}

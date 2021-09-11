package ru.lanit.test.controller.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.test.controller.person.PersonController;
import ru.lanit.test.model.car.Car;
import ru.lanit.test.model.person_with_cars.PersonWithCars;
import ru.lanit.test.service.IService;
import ru.lanit.test.validation.PostValidation;

import java.util.Map;

@RestController
public class CarController {
    private final IService<Car> carService;
    private final PostValidation personValidation;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public CarController(IService<Car> carService, @Qualifier("carValidation") PostValidation personValidation) {
        this.carService = carService;
        this.personValidation = personValidation;
    }



    @PostMapping(value = "/car")
    public ResponseEntity<String> saveCar(@RequestBody Map<String, String> carInformation) {
        ResponseEntity<String> validation = personValidation.validation(carInformation);
        if (validation.getStatusCode().is2xxSuccessful()) {
            Car car = createCar(carInformation);
            carService.save(car);
        }
        return validation;
    }

    private Car createCar(Map<String, String> carInformation) {
        Car car = new Car();
        car.setId(Long.parseLong(carInformation.get("id")));
        car.setHorsepower(Integer.parseInt(carInformation.get("horsepower")));
        car.setOwnerId(Integer.parseInt(carInformation.get("owner")));

        String[] vendorModel = carInformation.get("model").split("-");
        car.setVendor(vendorModel[0]);
        car.setModel(vendorModel[1]);
        return car;
    }
}

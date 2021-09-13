package ru.lanit.test.create.car;

import org.springframework.stereotype.Component;
import ru.lanit.test.create.CreateEntityForPostMethod;
import ru.lanit.test.model.car.Car;

import java.util.Map;

@Component("createCar")
public class CreateCar implements CreateEntityForPostMethod<Car> {
    @Override
    public Car createEntity(Map<String, String> carInformation) {
        Car car = new Car();
        car.setId(Long.parseLong(carInformation.get("id")));
        car.setHorsepower(Integer.parseInt(carInformation.get("horsepower")));
        car.setOwnerId(Integer.parseInt(carInformation.get("owner")));

        String[] vendorModel = carInformation.get("model").split("-");
        car.setVendor(vendorModel[0].toUpperCase());
        car.setModel(vendorModel[1]);
        return car;
    }
}

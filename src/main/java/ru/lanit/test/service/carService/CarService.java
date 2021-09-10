package ru.lanit.test.service.carService;

import org.springframework.stereotype.Service;
import ru.lanit.test.model.car.Car;
import ru.lanit.test.repository.carRepository.CarRepository;
import ru.lanit.test.service.IService;

@Service
public class CarService implements IService<Car> {
    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Car car) {
        repository.save(car);
    }

}

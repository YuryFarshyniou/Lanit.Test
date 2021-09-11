package ru.lanit.test.service.carService.impl;

import org.springframework.stereotype.Service;
import ru.lanit.test.model.car.Car;
import ru.lanit.test.repository.carRepository.CarRepository;
import ru.lanit.test.service.carService.ICarService;

import java.util.List;

@Service("carService")
public class CarService implements ICarService {
    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Car car) {
        repository.save(car);
    }

    @Override
    public Long findCarId(long id) {
        return repository.findCarId(id);
    }

    @Override
    public List<Car> findAllByOwnerId(long id) {
        return repository.findAllByOwnerId(id);
    }
}

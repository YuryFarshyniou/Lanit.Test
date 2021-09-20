package ru.lanit.test.service.carService.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.test.model.Car;
import ru.lanit.test.repository.CarRepository;
import ru.lanit.test.service.carService.ICarService;

import java.util.List;

@Service("carService")
public class CarService implements ICarService {
    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional()
    public void save(Car car) {
        repository.save(car);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findCarId(long id) {
        return repository.findCarId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAllByOwnerId(long id) {
        return repository.findAllByOwnerId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countCar() {
        return repository.countCar();
    }

    @Override
    @Transactional(readOnly = true)
    public Long countCarVendor() {
        return repository.countCarVendor();
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
}

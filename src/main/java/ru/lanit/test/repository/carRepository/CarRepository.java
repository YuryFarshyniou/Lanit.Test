package ru.lanit.test.repository.carRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lanit.test.model.car.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}

package ru.lanit.test.repository.carRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lanit.test.model.car.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select c.id from Car c where c.id=:carID")
    Long findCarId(long carID);

    List<Car> findAllByOwnerId(long ownerId);
}

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

    @Query("select COUNT(c.id) from Car c")
    Long countCar();

    @Query("select count(distinct c.vendor) from Car c")
    Long countCarVendor();
}

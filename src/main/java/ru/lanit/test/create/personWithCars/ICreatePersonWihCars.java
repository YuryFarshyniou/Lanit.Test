package ru.lanit.test.create.personWithCars;

import ru.lanit.test.model.car.Car;
import ru.lanit.test.model.person.Person;
import ru.lanit.test.model.person_with_cars.PersonWithCars;

import java.util.List;

public interface ICreatePersonWihCars {

    PersonWithCars createPersonWithCars(Person person, List<Car> cars);
}

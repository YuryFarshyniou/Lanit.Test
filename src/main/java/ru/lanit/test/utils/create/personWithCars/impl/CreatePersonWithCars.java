package ru.lanit.test.utils.create.personWithCars.impl;

import org.springframework.stereotype.Component;
import ru.lanit.test.utils.create.personWithCars.ICreatePersonWihCars;
import ru.lanit.test.model.car.Car;
import ru.lanit.test.model.person.Person;
import ru.lanit.test.model.person_with_cars.PersonWithCars;

import java.util.List;

@Component
public class CreatePersonWithCars implements ICreatePersonWihCars {
    @Override
    public PersonWithCars createPersonWithCars(Person person, List<Car> cars) {
        PersonWithCars personWithCars = new PersonWithCars();
        personWithCars.setId(person.getId());
        personWithCars.setBirthdate(person.getBirthdate());
        personWithCars.setName(person.getName());
        personWithCars.setCars(cars);
        return personWithCars;
    }
}

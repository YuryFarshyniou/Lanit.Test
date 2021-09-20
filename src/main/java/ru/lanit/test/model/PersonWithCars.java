package ru.lanit.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lanit.test.model.Car;
import ru.lanit.test.model.Person;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PersonWithCars {
    private long id;
    private String name;
    private Date birthdate;
    private List<Car> cars;

    public PersonWithCars(Person person, List<Car> cars) {
        this.id = person.getId();
        this.birthdate = person.getBirthdate();
        this.name = person.getName();
        this.cars = cars;
    }
}

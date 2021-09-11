package ru.lanit.test.model.person_with_cars;

import lombok.*;
import ru.lanit.test.model.car.Car;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class PersonWithCars {
    long id;
    String name;
    Date birthdate;
    List<Car> cars;

}

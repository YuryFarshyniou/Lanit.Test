package ru.lanit.test.model.person_with_cars;

import lombok.*;
import ru.lanit.test.model.car.Car;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PersonWithCars {
    private long id;
    private String name;
    private Date birthdate;
    private List<Car> cars;

}

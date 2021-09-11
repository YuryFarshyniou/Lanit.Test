package ru.lanit.test.validation.person_with_cars;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.lanit.test.model.car.Car;
import ru.lanit.test.model.person.Person;
import ru.lanit.test.model.person_with_cars.PersonWithCars;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.service.personService.IPersonService;
import ru.lanit.test.validation.GetValidation;

import java.util.List;

@Component
public class PersonWithCarsValidation implements GetValidation<PersonWithCars> {
    private final IPersonService personService;
    private final ICarService carService;

    public PersonWithCarsValidation(IPersonService personService,
                                    ICarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    @Override
    public ResponseEntity<PersonWithCars> validation(String id) {
        if (id.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long personId = Long.parseLong(id);
        Person person = personService.findPersonById(personId);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Car> cars = carService.findAllByOwnerId(personId);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PersonWithCars personWithCars = createPersonWithCars(person, cars);

        return new ResponseEntity<>(personWithCars, HttpStatus.OK);
    }

    private PersonWithCars createPersonWithCars(Person person, List<Car> cars) {
        PersonWithCars personWithCars = new PersonWithCars();
        personWithCars.setId(person.getId());
        personWithCars.setBirthdate(person.getBirthdate());
        personWithCars.setName(person.getName());
        personWithCars.setCars(cars);
        return personWithCars;
    }

}

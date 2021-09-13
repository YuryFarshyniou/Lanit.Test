package ru.lanit.test.utils.validation.person_with_cars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.lanit.test.utils.create.personWithCars.ICreatePersonWihCars;
import ru.lanit.test.model.car.Car;
import ru.lanit.test.model.person.Person;
import ru.lanit.test.model.person_with_cars.PersonWithCars;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.service.personService.IPersonService;
import ru.lanit.test.utils.validation.GetValidation;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PersonWithCarsValidation implements GetValidation<PersonWithCars> {
    private final IPersonService personService;
    private final ICarService carService;
    private final ICreatePersonWihCars createPersonWithCars;
    private final Logger logger = LoggerFactory.getLogger(PersonWithCarsValidation.class);

    public PersonWithCarsValidation(IPersonService personService, ICarService carService,
                                    ICreatePersonWihCars createPersonWithCars) {
        this.personService = personService;
        this.carService = carService;
        this.createPersonWithCars = createPersonWithCars;
    }

    @Override
    public ResponseEntity<PersonWithCars> validation(String id) {
        if (!validationId(id)) {
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
        PersonWithCars personWithCars = createPersonWithCars.createPersonWithCars(person, cars);

        return new ResponseEntity<>(personWithCars, HttpStatus.OK);
    }

    private boolean validationId(String id) {
        if (id.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d*$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) {
            try {
                long personId = Long.parseLong(matcher.group());
            } catch (NumberFormatException e) {
                logger.warn("Too big id number!");
                return false;
            }
            return true;
        } else {
            logger.warn("This id is not a number!");
            return false;
        }
    }

}

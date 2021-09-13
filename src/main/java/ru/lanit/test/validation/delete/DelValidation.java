package ru.lanit.test.validation.delete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.service.personService.IPersonService;
import ru.lanit.test.validation.DeleteValidation;

@Component
public class DelValidation implements DeleteValidation {
    private final IPersonService personService;
    private final ICarService carService;
    private final Logger logger = LoggerFactory.getLogger(DelValidation.class);

    public DelValidation(IPersonService personService, ICarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    @Override
    public ResponseEntity<String> deleteValidation() {
        try {
            personService.deleteAll();
            carService.deleteAll();
        } catch (Exception e) {
            logger.warn("There were problem with delete operation");
            return new ResponseEntity<>("There were problems with delete operation", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>("Delete operation was success!", HttpStatus.OK);
    }
}

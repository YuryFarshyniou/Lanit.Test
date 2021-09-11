package ru.lanit.test.validation.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.service.personService.IPersonService;
import ru.lanit.test.validation.PostValidation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("carValidation")
public class CarValidation implements PostValidation {
    private final Logger logger = LoggerFactory.getLogger(CarValidation.class);
    private final IPersonService personService;
    private final ICarService carService;

    public CarValidation(@Qualifier("personService") IPersonService personService,
                         @Qualifier("carService") ICarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    @Override
    public ResponseEntity<String> validation(Map<String, String> jsonMap) {
        List<Boolean> valid = validateAll(jsonMap);
        for (Boolean validation : valid) {
            if (!validation) {
                return new ResponseEntity<>("Validation problem", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    private List<Boolean> validateAll(Map<String, String> jsonMap) {
        List<Boolean> allValidations = new ArrayList<>();
        allValidations.add(validationId(jsonMap.get("id")));
        allValidations.add(validationHorsepower(jsonMap.get("horsepower")));
        allValidations.add(validationModel(jsonMap.get("model")));
        allValidations.add(isNotPresentCarInDB(jsonMap.get("id")));
        allValidations.add(validationOwner(jsonMap.get("owner")));
        return allValidations;
    }

    private boolean validationId(String id) {
        if (id.isEmpty()) {
            logger.warn("Car id is null!");
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d*$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find()) {
            try {
                long carId = Long.parseLong(matcher.group());
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

    private boolean validationHorsepower(String horsepower) {
        if (horsepower.isEmpty()) {
            logger.warn("Horsepower is null!");
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d*$");
        Matcher matcher = pattern.matcher(horsepower);

        if (matcher.find()) {
            int horsePower;
            try {
                horsePower = Integer.parseInt(matcher.group());
            } catch (NumberFormatException e) {
                logger.warn("Too big horsepower number!");
                return false;
            }
            return horsePower > 0;
        }
        logger.warn("Horsepower is not a number!");
        return false;
    }

    private boolean validationModel(String model) {
        if (model.isEmpty()) {
            return false;
        }
        String[] vendorModel = model.split("-");
        if (vendorModel.length == 2) {
            return true;
        } else {
            logger.warn("Wrong car model!");
            return false;
        }
    }

    private boolean validationOwner(String ownerId) {
        if (ownerId.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d*$");
        Matcher matcher = pattern.matcher(ownerId);

        if (matcher.find()) {
            try {
                long owner = Integer.parseInt(matcher.group());
            } catch (NumberFormatException e) {
                logger.warn("Too big ownerId number!");
                return false;
            }
        }
        long id = Long.parseLong(ownerId);
        Date date = personService.findPersonDate(id);
        if (date == null) {
            logger.warn("This person doesn't exist");
            return false;
        }
        int years = getOwnerAge(date);
        return years >= 18;
    }

    private boolean isNotPresentCarInDB(String id) {
        if (validationId(id)) {
            Long carId = carService.findCarId(Long.parseLong(id));
            if (carId == null) {
                return true;
            } else {
                logger.warn("Person with this id has already exist");
                return false;
            }
        }
        logger.warn("Wrong Id validation.");
        return false;
    }

    private int getOwnerAge(Date date) {
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        Calendar birthdate = Calendar.getInstance();
        birthdate.setTimeInMillis(date.getTime());
        now.setTimeInMillis(currentTime);
        return now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
    }
}

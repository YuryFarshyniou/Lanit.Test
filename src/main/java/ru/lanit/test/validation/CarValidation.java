package ru.lanit.test.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.lanit.test.exception.ValidationException;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.service.personService.IPersonService;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("carValidation")
public class CarValidation implements PostValidation {
    private final Logger logger = LoggerFactory.getLogger(CarValidation.class);
    private final IPersonService personService;
    private final ICarService carService;

    public CarValidation(IPersonService personService,
                         ICarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    @Override
    public void validation(Map<String, String> jsonMap) throws ValidationException {
        validateId(jsonMap.get("id"));
        validationHorsepower(jsonMap.get("horsepower"));
        validationModel(jsonMap.get("model"));
        validationOwner(jsonMap.get("owner"));
    }

    private void validateId(String id) throws ValidationException {
        checkStringIsEmpty(id);
        checkThisStringISaNumber(id);
        isPresentCarInDB(id);
    }

    private void validationHorsepower(String horsepower) throws ValidationException {
        checkStringIsEmpty(horsepower);
        checkThisStringISaNumber(horsepower);
        checkHorsePower(horsepower);
    }

    private void validationModel(String model) throws ValidationException {
        checkStringIsEmpty(model);
        checkCarModel(model);
    }

    private void validationOwner(String ownerId) throws ValidationException {
        checkStringIsEmpty(ownerId);
        checkThisStringISaNumber(ownerId);
        checkPersonBirthdate(ownerId);
    }

    private void isPresentCarInDB(String id) throws ValidationException {
        Long carId = carService.findCarId(Long.parseLong(id));
        if (carId != null) {
            logger.warn("Car with this id has already exist");
            throw new ValidationException("Car with this id has already exists");
        }
    }

    private int getOwnerAge(Date date) {
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        Calendar birthdate = Calendar.getInstance();
        birthdate.setTimeInMillis(date.getTime());
        now.setTimeInMillis(currentTime);
        return now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
    }

    private void checkStringIsEmpty(String id) throws ValidationException {
        if (id.isEmpty()) {
            throw new ValidationException("String is empty!");
        }
    }

    private void checkThisStringISaNumber(String row) throws ValidationException {
        Pattern pattern = Pattern.compile("^\\d*$");
        Matcher matcher = pattern.matcher(row);
        if (matcher.find()) {
            try {
                long number = Long.parseLong(matcher.group());
            } catch (NumberFormatException e) {
                logger.warn("This number is too big!");
                throw new ValidationException("This number is too big!");
            }
        } else {
            logger.warn("This string is not a number!");
            throw new ValidationException("This string is not a number!");
        }
    }

    private void checkHorsePower(String horsePower) throws ValidationException {
        long horsePowerNumber = Long.parseLong(horsePower);
        if (horsePowerNumber < 1) {
            throw new ValidationException("Car's horsepower value less than 1");
        }
    }

    private void checkCarModel(String carModel) throws ValidationException {
        String[] vendorModel = carModel.split("-");
        if (vendorModel.length != 2) {
            logger.warn("Car's model isn't correspond to the condition");
            throw new ValidationException("Car's model isn't correspond to the condition");
        }
    }

    private void checkPersonBirthdate(String ownerId) throws ValidationException {
        long id = Long.parseLong(ownerId);
        Date date = personService.findPersonDate(id);
        checkDateISNull(date);
        checkAge(date);
    }

    private void checkDateISNull(Date date) throws ValidationException {
        if (date == null) {
            logger.warn("This person doesn't exist");
            throw new ValidationException("Car's owner doesn't exist");

        }
    }

    private void checkAge(Date date) throws ValidationException {
        int age = getOwnerAge(date);
        if (age < 18) {
            logger.warn("Owner's age is less than 18");
            throw new ValidationException("Owner's age is less than 18");
        }
    }

}

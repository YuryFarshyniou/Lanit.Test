package ru.lanit.test.utils.validation.statistics.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.lanit.test.controller.person.PersonController;
import ru.lanit.test.model.statistics.Statistics;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.service.personService.IPersonService;
import ru.lanit.test.utils.validation.statistics.IStatisticsValidation;

@Component
public class StatisticsValidation implements IStatisticsValidation {
    private final IPersonService personService;
    private final ICarService carService;
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public StatisticsValidation(IPersonService personService, ICarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    @Override
    public ResponseEntity<Statistics> validation() {
        Statistics statistics = createStatistics();
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    private Statistics createStatistics() {
        Statistics statistics = new Statistics();
        statistics.setPersonCount(personService.countPerson());
        statistics.setUniqueVendorCount(carService.countCarVendor());
        statistics.setCarCount(carService.countCar());
        return statistics;
    }
}

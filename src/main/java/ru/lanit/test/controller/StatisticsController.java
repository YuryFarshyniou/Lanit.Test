package ru.lanit.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.lanit.test.model.Statistics;
import ru.lanit.test.service.carService.ICarService;
import ru.lanit.test.service.personService.IPersonService;

@Controller
public class StatisticsController {
    private final IPersonService personService;
    private final ICarService carService;
    private final Logger logger = LoggerFactory.getLogger(StatisticsController.class);


    public StatisticsController(IPersonService personService,
                                ICarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    @GetMapping("/statistics")
    public ResponseEntity<Statistics> getStatistics() {
        Statistics statistics;
            Long personNumber = personService.countPerson();
            Long carNumber = carService.countCar();
            Long carVendorNumber = carService.countCarVendor();
            statistics = new Statistics(personNumber,
                    carNumber,
                    carVendorNumber);

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Something wrong with Statistics!")
    @ExceptionHandler
    public void handleExceptions(Exception e) {
        logger.warn("Problem with statistics!");
    }

}

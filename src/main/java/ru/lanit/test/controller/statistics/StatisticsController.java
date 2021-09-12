package ru.lanit.test.controller.statistics;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.lanit.test.model.statistics.Statistics;
import ru.lanit.test.validation.statistics.IStatisticsValidation;

@Controller
public class StatisticsController {
    private final IStatisticsValidation statisticsValidation;

    public StatisticsController(IStatisticsValidation statisticsValidation) {
        this.statisticsValidation = statisticsValidation;
    }

    @GetMapping("/statistics")
    public ResponseEntity<Statistics> getStatistics() {
        return statisticsValidation.validation();
    }

}

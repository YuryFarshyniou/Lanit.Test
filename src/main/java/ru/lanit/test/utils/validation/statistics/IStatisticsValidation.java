package ru.lanit.test.utils.validation.statistics;

import org.springframework.http.ResponseEntity;
import ru.lanit.test.model.statistics.Statistics;

public interface IStatisticsValidation {
    ResponseEntity<Statistics> validation();
}

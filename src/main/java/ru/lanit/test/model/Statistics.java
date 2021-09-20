package ru.lanit.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistics {
    private long personCount;
    private long carCount;
    private long uniqueVendorCount;
}

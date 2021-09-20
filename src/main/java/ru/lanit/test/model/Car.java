package ru.lanit.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Data
@Table
@NoArgsConstructor
public class Car {

    @Id
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String vendor;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int horsepower;

    @Column(nullable = false)
    private long ownerId;

    public Car(Map<String, String> carInformation) {
        this.id = Long.parseLong(carInformation.get("id"));
        this.horsepower = Integer.parseInt(carInformation.get("horsepower"));
        this.ownerId = Integer.parseInt(carInformation.get("owner"));
        String[] vendorModel = carInformation.get("model").split("-");
        this.vendor = vendorModel[0].toUpperCase();
        this.model = vendorModel[1];
    }

}

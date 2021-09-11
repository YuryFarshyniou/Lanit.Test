package ru.lanit.test.model.car;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Car {

    @Id
    @GeneratedValue(generator = "increment")
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


}

package ru.lanit.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Entity
@Table
@Data
@NoArgsConstructor
public class Person {

    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthdate;

    @SneakyThrows
    public Person(Map<String, String> personInformation) {
        this.id = Long.parseLong(personInformation.get("id"));
        this.name = personInformation.get("name");
        this.birthdate = new SimpleDateFormat("dd.MM.yyyy").parse(personInformation.get("birthdate"));

    }
}

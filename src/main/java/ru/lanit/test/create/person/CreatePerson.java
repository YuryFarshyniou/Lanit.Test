package ru.lanit.test.create.person;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.lanit.test.create.CreateEntityForPostMethod;
import ru.lanit.test.model.person.Person;

import java.text.SimpleDateFormat;
import java.util.Map;

@Component("createPerson")
public class CreatePerson implements CreateEntityForPostMethod<Person> {

    @Override
    @SneakyThrows
    public Person createEntity(Map<String, String> personInformation) {
        Person person1 = new Person();
        person1.setId(Long.parseLong(personInformation.get("id")));
        person1.setName(personInformation.get("name"));
        person1.setBirthdate(new SimpleDateFormat("dd.MM.yyyy").parse(personInformation.get("birthdate")));
        return person1;
    }
}

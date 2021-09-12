package ru.lanit.test.service.personService;

import ru.lanit.test.model.person.Person;
import ru.lanit.test.service.IService;

import java.util.Date;

public interface IPersonService extends IService<Person> {
    Long findPersonId(long id);

    Date findPersonDate(long id);

    Person findPersonById(long personId);

    Long countPerson();

    void deleteAll();
}

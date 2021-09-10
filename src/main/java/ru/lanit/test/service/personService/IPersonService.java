package ru.lanit.test.service.personService;

import ru.lanit.test.model.person.Person;
import ru.lanit.test.service.IService;

public interface IPersonService extends IService<Person> {
    Long findPersonId(long id);
}

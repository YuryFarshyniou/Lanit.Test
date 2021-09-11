package ru.lanit.test.service.personService.impl;

import org.springframework.stereotype.Service;
import ru.lanit.test.model.person.Person;
import ru.lanit.test.repository.personRepository.PersonRepository;
import ru.lanit.test.service.personService.IPersonService;

import java.util.Date;

@Service("personService")
public class PersonService implements IPersonService {
    private PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Person person) {

        repository.save(person);
    }

    @Override
    public Long findPersonId(long id) {
        return repository.findPersonId(id);
    }

    @Override
    public Date findPersonDate(long id) {
        return repository.findPersonDate(id);
    }

    @Override
    public Person findPersonById(long personId) {

        return repository.findPersonById(personId);
    }
}

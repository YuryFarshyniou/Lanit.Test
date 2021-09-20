package ru.lanit.test.service.personService.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.test.model.Person;
import ru.lanit.test.repository.PersonRepository;
import ru.lanit.test.service.personService.IPersonService;

import java.util.Date;

@Service("personService")
public class PersonService implements IPersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void save(Person person) {
        repository.save(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findPersonId(long id) {
        return repository.findPersonId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Date findPersonDate(long id) {
        return repository.findPersonDate(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Person findPersonById(long personId) {
        return repository.findPersonById(personId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countPerson() {
        return repository.countPerson();
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }
}

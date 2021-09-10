package ru.lanit.test.service.personService;

import org.springframework.stereotype.Service;
import ru.lanit.test.model.person.Person;
import ru.lanit.test.repository.personRepository.PersonRepository;

@Service
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
}

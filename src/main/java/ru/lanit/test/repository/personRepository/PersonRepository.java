package ru.lanit.test.repository.personRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lanit.test.model.person.Person;

import java.util.Date;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p.id from Person p where p.id=:personID")
    Long findPersonId(long personID);

    @Query("select p.birthdate from Person p where p.id=:personID")
    Date findPersonDate(long personID);

    Person findPersonById(long personId);

    @Query("select COUNT(p.id) from Person p")
    Long countPerson();
}

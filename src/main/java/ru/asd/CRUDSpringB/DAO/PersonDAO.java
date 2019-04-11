package ru.asd.CRUDSpringB.DAO;

import ru.asd.CRUDSpringB.entity.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> listAll();

    void addPerson(Person person);

    void deletePersonById(Long id);

    Person getPersonById(Long id);
    Person updatePerson(Person person);
}

package ru.asd.CRUDSpringB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.asd.CRUDSpringB.DAO.PersonDAO;
import ru.asd.CRUDSpringB.entity.Person;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonDAO personDAO;

    @Override
    public List<Person> getAll() {
        List<Person> persList = new ArrayList<>();

        return personDAO.listAll();
    }

    public void savePerson(Person person) {
        personDAO.addPerson(person);
    }

    @Override
    public void deletePersonById(Long id) {
        personDAO.deletePersonById(id);
    }

    @Override
    public void addPerson(Person person) {
        savePerson(person);
    }

    @Override
    public Person getPersonById(Long id) {
        return personDAO.getPersonById(id);
    }

    @Override
    public Person updatePerson(Person person) {
        Person res = personDAO.updatePerson(person);
        return res;
    }
}

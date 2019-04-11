package ru.asd.CRUDSpringB.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.asd.CRUDSpringB.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PersonDAOHibernateImpl implements PersonDAO {

    @PersistenceContext
    private EntityManager eMrF;

    @Override
    public Person updatePerson(Person person) {
        Person p = getPersonById(person.getId());
        System.out.println(p);
        p.setSurname(person.getSurname());
        p.setName(person.getName());
        p.setAddress(person.getAddress());
        return p;
    }

    @Override
    public List<Person> listAll() {
        return eMrF.createQuery("from Person").getResultList();
    }

    @Override
    public void addPerson(Person person) {
        eMrF.persist(person);
    }

    @Override
    public void deletePersonById(Long id) {
        Person personForDelete = getPersonById(id);
        eMrF.remove(personForDelete);
    }

    @Override
    public Person getPersonById(Long id) {
        Person person = eMrF.find(Person.class, id);
        return person;
        // return (Person) eMrF.createQuery("from Person as p where p.id=:id").setParameter("id", id).getSingleResult();
    }
}

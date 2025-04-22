package collections;

import model.AppUser;
import model.Person;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOCollectionTest {


    Person creator;
    PersonDAOCollection personDAOCollection = new PersonDAOCollection();
    Person person;
    Person registeredPerson;


    @BeforeEach
    void setUp() {
        creator = new Person("Kalle", "Anka", "kalle@anka.com");
        personDAOCollection.persist(creator);

        person = personDAOCollection.persist(creator);
        Person person1 = new Person("Joakim", "von Anka", "jv@anka.com");

        registeredPerson = personDAOCollection.persist(person1);
    }

    @Test
    void persist() {
        Assertions.assertSame(person, creator);
        Assertions.assertTrue(creator.getId() > 0);
        Assertions.assertTrue(personDAOCollection.personArrayList.contains(person));
        Assertions.assertNotEquals(creator.getId(), registeredPerson.getId());
    }

    @Test
    void findById() {
        Person model;
        model = personDAOCollection.findById(2);

        Assertions.assertEquals(2, model.getId());

    }

    @Test
    void notFindById(){

        Person model = personDAOCollection.findById(1);

        Assertions.assertEquals(null, model);

    }

    @Test
    void findByEmail() {
        Person model;
        model = personDAOCollection.findByEmail("jv@anka.com");

        Assertions.assertEquals(registeredPerson ,model);
    }

    @Test
    void notFindByEmail(){

        Person model = personDAOCollection.findByEmail("NoSuch@email.com");

        Assertions.assertEquals(null, model);

    }

    @Test
    void findAll() {
        ArrayList<Person> personArrayList1;
        personArrayList1 = personDAOCollection.findAll();

        Assertions.assertEquals(personArrayList1, personDAOCollection.personArrayList);
    }

    @Test
    void remove() {

        ArrayList<Person> appUsers1 = new ArrayList<>();
        ArrayList<Person> appUsers2;

        personDAOCollection.remove(2);
        personDAOCollection.remove(3);
        appUsers2 = personDAOCollection.findAll();

        Assertions.assertEquals(appUsers1.size(), appUsers2.size());
    }
}
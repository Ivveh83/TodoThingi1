package DAO;

import model.Person;

import java.util.ArrayList;

public interface PersonDAO {
    Person persist(Person person);
    Person findByUsername(String username);
    Person findByEmail(String email);
    ArrayList<Person> findAll();
    void remove(int id);
}

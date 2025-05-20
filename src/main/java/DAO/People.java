package DAO;

import model.Person;

import java.util.Collection;

public interface People extends Base<Person> {
    Collection<Person> findByName(String name);
}

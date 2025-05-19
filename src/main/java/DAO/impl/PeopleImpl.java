package DAO.impl;

import DAO.People;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PeopleImpl implements People {

    private Connection connection;

    public PeopleImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Person create(Person person) {
        String sql = "INSERT INTO person (first_name, last_name) VALUES(?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);

            if (affectedRows > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int generatedPersonId = resultSet.getInt(1);
                    System.out.println("generatedPersonId = " + generatedPersonId);
                    person.setPersonId(generatedPersonId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving person");
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public Collection<Person> findAll() {

        Collection<Person> personCollection = new ArrayList<>();
        String sql = "SELECT person_id, first_name, last_name FROM person";
        try (Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Person person = new Person(
                        rs.getInt("person_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
                personCollection.add(person);
            }

        }catch (SQLException e) {
            System.out.println("Error collecting person");
            e.printStackTrace();
        }
        return personCollection;
    }

    @Override
    public Person findById(int id) {
        String sql = "SELECT person_id, first_name, last_name FROM person WHERE person_id = ?";
        try (PreparedStatement findById = connection.prepareStatement(sql)){
            findById.setInt(1, id);
            ResultSet rs = findById.executeQuery();
            while (rs.next()) {
                return new Person(
                        rs.getInt("person_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error when finding by Id");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Person> findByName(String name) {
        Collection<Person> personArrayList = new ArrayList<>();
        String sql = "SELECT person_id, first_name, last_name FROM person WHERE first_name = ?";
        try (PreparedStatement findByName = connection.prepareStatement(sql)) {
            findByName.setString(1, name);
            ResultSet rs = findByName.executeQuery();
            while (rs.next()) {
                personArrayList.add(new Person(
                        rs.getInt("person_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name")));
            }
        }catch (SQLException e) {
            System.out.println("Error when finding by Name");
            e.printStackTrace();
        }
        return personArrayList;
    }

    @Override
    public Person update(Person person) {
        String sql = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";
        try (PreparedStatement updatePerson = connection.prepareStatement(sql)) {

            updatePerson.setString(1, person.getFirstName());
            updatePerson.setString(2, person.getLastName());
            updatePerson.setInt(3, person.getPersonId());
            int rowsAffected = updatePerson.executeUpdate();
            System.out.println("rowsAffected : " + rowsAffected);
            if (rowsAffected > 0) {
                return findById(person.getPersonId()); //Returns updated person
            }
        }catch (SQLException e) {
            System.out.println("Error by updating Person");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM person WHERE person_id = ?";
        try {
            PreparedStatement deletePerson = connection.prepareStatement(sql);
            deletePerson.setInt(1, id);
            int rowsAffected = deletePerson.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error by deleting person");
            e.printStackTrace();
        }
        return false;
    }
}

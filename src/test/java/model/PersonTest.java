package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sequenzers.PersonIdSequencer;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @BeforeEach
    void setUp() {
        PersonIdSequencer.setCurrentId(0); // Återställ counter före varje test
    }

    @Test
    void testConstructorValidInput() {
        Person person = new Person("Anna", "Andersson", "anna@example.com");
        assertEquals(1, person.getId());
        assertEquals("Anna", person.getFirstName());
        assertEquals("Andersson", person.getLastName());
        assertEquals("anna@example.com", person.getEmail());
    }

    @Test
    void testConstructorWithDefaultEmail() {
        Person person = new Person("Anna", "Andersson");
        assertEquals(1, person.getId());
        assertEquals("Anna", person.getFirstName());
        assertEquals("Andersson", person.getLastName());
        assertEquals("mail@example.com", person.getEmail());
    }

    @Test
    void testConstructorNullFirstNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person(null, "Andersson", "anna@example.com");
        });
        assertEquals("First name is not allowed to be null", exception.getMessage());
    }

    @Test
    void testConstructorNullLastNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person("Anna", null, "anna@example.com");
        });
        assertEquals("Last name is not allowed to be null", exception.getMessage());
    }

    @Test
    void testConstructorNullEmailThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person("Anna", "Andersson", null);
        });
        assertEquals("Email is not allowed to be null", exception.getMessage());
    }

    @Test
    void testCounterIncrements() {
        Person person1 = new Person("Anna", "Andersson");
        Person person2 = new Person("Ben", "Bengtsson");
        assertEquals(1, person1.getId());
        assertEquals(2, person2.getId());
    }

    @Test
    void testSetFirstNameValid() {
        Person person = new Person("Anna", "Andersson", "anna@example.com");
        person.setFirstName("Maria");
        assertEquals("Maria", person.getFirstName());
    }

    @Test
    void testSetFirstNameNullThrowsException() {
        Person person = new Person("Anna", "Andersson", "anna@example.com");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setFirstName(null);
        });
        assertEquals("First name is not allowed to be null", exception.getMessage());
        assertEquals("Anna", person.getFirstName()); // Förnamn ska inte ändras
    }

    @Test
    void testSetLastNameValid() {
        Person person = new Person("Anna", "Andersson", "anna@example.com");
        person.setLastName("Svensson");
        assertEquals("Svensson", person.getLastName());
    }

    @Test
    void testSetLastNameNullThrowsException() {
        Person person = new Person("Anna", "Andersson", "anna@example.com");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setLastName(null);
        });
        assertEquals("Last name is not allowed to be null", exception.getMessage());
        assertEquals("Andersson", person.getLastName());
    }

    @Test
    void testSetEmailValid() {
        Person person = new Person("Anna", "Andersson", "anna@example.com");
        person.setEmail("new@example.com");
        assertEquals("new@example.com", person.getEmail());
    }

    @Test
    void testSetEmailNullThrowsException() {
        Person person = new Person("Anna", "Andersson", "anna@example.com");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            person.setEmail(null);
        });
        assertEquals("Email is not allowed to be null", exception.getMessage());
        assertEquals("anna@example.com", person.getEmail());
    }

    @Test
    void testSetCredentials() {
        Person person = new Person("Anna", "Andersson", "anna@example.com");
        AppUser credentials = new AppUser("anna", "pass123", AppRole.ROLE_APP_USER);
        person.setCredentials(credentials);
        assertEquals(credentials, person.getCredentials());
    }
}
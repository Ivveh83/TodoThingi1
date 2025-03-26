
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    public class PersonTest {

        @BeforeEach
        void setUp() {
            // Återställ counter före varje test för att säkerställa oberoende tester
            Person.counter = 0;
        }

        @Test
        void testConstructorValidInput() {
            // Testar att konstruktorn fungerar med giltiga värden
            Person person = new Person("Anna", "Andersson", "anna@example.com");
            assertEquals(1, person.getId(), "ID ska vara 1 för första instansen");
            assertEquals("Anna", person.getFirstName(), "Förnamn ska matcha");
            assertEquals("Andersson", person.getLastName(), "Efternamn ska matcha");
            assertEquals("anna@example.com", person.getEmail(), "E-post ska matcha");
        }

        @Test
        void testConstructorNullFirstNameThrowsException() {
            // Testar att konstruktorn kastar IllegalArgumentException vid null förnamn
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Person(null, "Andersson", "anna@example.com");
            });
            assertEquals("Must not be null", exception.getMessage(), "Felmeddelande ska matcha");
        }

        @Test
        void testConstructorNullLastNameThrowsException() {
            // Testar att konstruktorn kastar IllegalArgumentException vid null efternamn
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Person("Anna", null, "anna@example.com");
            });
            assertEquals("Must not be null", exception.getMessage(), "Felmeddelande ska matcha");
        }

        @Test
        void testConstructorNullEmailThrowsException() {
            // Testar att konstruktorn kastar IllegalArgumentException vid null e-post
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Person("Anna", "Andersson", null);
            });
            assertEquals("Must not be null", exception.getMessage(), "Felmeddelande ska matcha");
        }

        @Test
        void testCounterIncrements() {
            // Testar att counter ökar för varje ny instans
            Person person1 = new Person("Anna", "Andersson", "anna@example.com");
            Person person2 = new Person("Ben", "Bengtsson", "ben@example.com");
            assertEquals(1, person1.getId(), "Första personens ID ska vara 1");
            assertEquals(2, person2.getId(), "Andra personens ID ska vara 2");
        }

        @Test
        void testSetFirstNameValid() {
            // Testar att setFirstName fungerar med giltigt värde
            Person person = new Person("Anna", "Andersson", "anna@example.com");
            person.setFirstName("Maria");
            assertEquals("Maria", person.getFirstName(), "Förnamn ska uppdateras till Maria");
        }

        @Test
        void testSetFirstNameNullDoesNotUpdate() {
            // Testar att setFirstName inte uppdaterar vid null
            Person person = new Person("Anna", "Andersson", "anna@example.com");
            person.setFirstName(null);
            assertEquals("Anna", person.getFirstName(), "Förnamn ska inte ändras vid null");
        }

        @Test
        void testSetLastNameValid() {
            // Testar att setLastName fungerar med giltigt värde
            Person person = new Person("Anna", "Andersson", "anna@example.com");
            person.setLastName("Svensson");
            assertEquals("Svensson", person.getLastName(), "Efternamn ska uppdateras till Svensson");
        }

        @Test
        void testSetLastNameNullDoesNotUpdate() {
            // Testar att setLastName inte uppdaterar vid null
            Person person = new Person("Anna", "Andersson", "anna@example.com");
            person.setLastName(null);
            assertEquals("Andersson", person.getLastName(), "Efternamn ska inte ändras vid null");
        }

        @Test
        void testSetEmailValid() {
            // Testar att setEmail fungerar med giltigt värde
            Person person = new Person("Anna", "Andersson", "anna@example.com");
            person.setEmail("anna.new@example.com");
            assertEquals("anna.new@example.com", person.getEmail(), "E-post ska uppdateras");
        }

        @Test
        void testSetEmailNullDoesNotUpdate() {
            // Testar att setEmail inte uppdaterar vid null
            Person person = new Person("Anna", "Andersson", "anna@example.com");
            person.setEmail(null);
            assertEquals("anna@example.com", person.getEmail(), "E-post ska inte ändras vid null");
        }
    }
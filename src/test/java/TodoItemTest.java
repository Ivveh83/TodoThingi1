import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTest {

    private Person creator;

    @BeforeEach
    void setUp() {
        // Återställ counter och skapa en standardskapare före varje test
        TodoItem.counter = 0;
        creator = new Person("Anna", "Andersson", "anna@example.com");
    }

    @Test
    void testConstructorValidInput() {
        // Testar konstruktorn med giltiga värden
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", "Do something", deadline, false, creator);
        assertEquals(1, item.getId(), "ID ska vara 1 för första instansen");
        assertEquals("Task 1", item.getTitle(), "Titeln ska matcha");
        assertEquals("Do something", item.getDescription(), "Beskrivningen ska matcha");
        assertEquals(deadline, item.getDeadLine(), "Deadline ska matcha");
        assertFalse(item.getDone(), "Done ska vara false");
        assertEquals(creator, item.getCreator(), "Skaparen ska matcha");
    }

    @Test
    void testConstructorNullTitleThrowsException() {
        // Testar att null-titel kastar undantag
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TodoItem(null, "Do something", deadline, false, creator);
        });
        assertEquals("Must not be null", exception.getMessage(), "Felmeddelande ska matcha");
    }

    @Test
    void testConstructorEmptyTitleThrowsException() {
        // Testar att tom titel kastar undantag
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TodoItem("", "Do something", deadline, false, creator);
        });
        assertEquals("Must not be empty", exception.getMessage(), "Felmeddelande ska matcha");
    }

    @Test
    void testConstructorBlankTitleThrowsException() {
        // Testar att titel med bara blanktecken kastar undantag
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TodoItem("   ", "Do something", deadline, false, creator);
        });
        assertEquals("Must not contain of only spaces", exception.getMessage(), "Felmeddelande ska matcha");
    }

    @Test
    void testConstructorNullDeadlineThrowsException() {
        // Testar att null-deadline kastar undantag
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TodoItem("Task 1", "Do something", null, false, creator);
        });
        assertEquals("Must not be null", exception.getMessage(), "Felmeddelande ska matcha");
    }

    @Test
    void testCounterIncrements() {
        // Testar att counter ökar för varje ny instans
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item1 = new TodoItem("Task 1", "Do something", deadline, false, creator);
        TodoItem item2 = new TodoItem("Task 2", "Do another thing", deadline, true, creator);
        assertEquals(1, item1.getId(), "Första item-ID ska vara 1");
        assertEquals(2, item2.getId(), "Andra item-ID ska vara 2");
    }

    @Test
    void testSetTitleValid() {
        // Testar att setTitle fungerar med giltigt värde
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", "Do something", deadline, false, creator);
        item.setTitle("New Task");
        assertEquals("New Task", item.getTitle(), "Titeln ska uppdateras");
    }

    @Test
    void testSetTitleNullDoesNotUpdate() {
        // Testar att setTitle inte uppdaterar vid null
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", "Do something", deadline, false, creator);
        item.setTitle(null);
        assertEquals("Task 1", item.getTitle(), "Titeln ska inte ändras vid null");
    }

    @Test
    void testSetTitleBlankDoesNotUpdate() {
        // Testar att setTitle inte uppdaterar vid blank titel
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", "Do something", deadline, false, creator);
        item.setTitle("   ");
        assertEquals("Task 1", item.getTitle(), "Titeln ska inte ändras vid blankt värde");
    }

    @Test
    void testSetDescriptionNull() {
        // Testar att setDescription accepterar null
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", "Do something", deadline, false, creator);
        item.setDescription(null);
        assertNull(item.getDescription(), "Beskrivningen ska vara null");
    }

    @Test
    void testSetDeadlineValid() {
        // Testar att setDeadLine fungerar med giltigt värde
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", "Do something", deadline, false, creator);
        LocalDate newDeadline = LocalDate.of(2026, 1, 1);
        item.setDeadLine(newDeadline);
        assertEquals(newDeadline, item.getDeadLine(), "Deadline ska uppdateras");
    }

    @Test
    void testSetDeadlineNullDoesNotUpdate() {
        // Testar att setDeadLine inte uppdaterar vid null
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", "Do something", deadline, false, creator);
        item.setDeadLine(null);
        assertEquals(deadline, item.getDeadLine(), "Deadline ska inte ändras vid null");
    }

    @Test
    void testSetDone() {
        // Testar att setDone fungerar
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", "Do something", deadline, false, creator);
        item.setDone(true);
        assertTrue(item.getDone(), "Done ska vara true efter uppdatering");
    }

    @Test
    void testSetCreatorNullDoesNotUpdate() {
        // Testar att setCreator inte uppdaterar vid null
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", "Do something", deadline, false, creator);
        item.setCreator(null);
        assertEquals(creator, item.getCreator(), "Skaparen ska inte ändras vid null");
    }

    @Test
    void testIsOverdueTrue() {
        // Testar att isOverdue returnerar true för en passerad deadline
        LocalDate pastDeadline = LocalDate.of(2020, 1, 1);
        TodoItem item = new TodoItem("Task 1", "Do something", pastDeadline, false, creator);
        assertTrue(item.isOverdue(), "Uppgiften ska vara försenad");
    }

    @Test
    void testIsOverdueFalse() {
        // Testar att isOverdue returnerar false för en framtida deadline
        LocalDate futureDeadline = LocalDate.of(2026, 1, 1);
        TodoItem item = new TodoItem("Task 1", "Do something", futureDeadline, false, creator);
        assertFalse(item.isOverdue(), "Uppgiften ska inte vara försenad");
    }
}
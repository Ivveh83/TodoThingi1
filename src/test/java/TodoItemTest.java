import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTest {

    private Person creator;

    @BeforeEach
    void setUp() {
        TodoItem.counter = 0;
        creator = new Person("Anna", "Andersson", "anna@example.com");
    }

    @Test
    void testConstructorValidInput() {
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", "Description", deadline, false, creator);
        assertEquals(1, item.getId());
        assertEquals("Task 1", item.getTitle());
        assertEquals("Description", item.getDescription());
        assertEquals(deadline, item.getDeadLine());
        assertFalse(item.getDone());
        assertEquals(creator, item.getCreator());
    }

    @Test
    void testConstructorWithDefaultValues() {
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", deadline);
        assertEquals(1, item.getId());
        assertEquals("Task 1", item.getTitle());
        assertNull(item.getDescription());
        assertEquals(deadline, item.getDeadLine());
        assertFalse(item.getDone());
        assertNull(item.getCreator());
    }

    @Test
    void testCounterIncrements() {
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item1 = new TodoItem("Task 1", deadline);
        TodoItem item2 = new TodoItem("Task 2", deadline);
        assertEquals(1, item1.getId());
        assertEquals(2, item2.getId());
    }

    @Test
    void testSetTitleValid() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setTitle("New Task");
        assertEquals("New Task", item.getTitle());
    }

    @Test
    void testSetTitleNullDoesNotUpdate() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setTitle(null);
        assertEquals("Task 1", item.getTitle());
    }

    @Test
    void testSetTitleEmptyDoesNotUpdate() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setTitle("");
        assertEquals("Task 1", item.getTitle());
    }

    @Test
    void testSetTitleBlankDoesNotUpdate() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setTitle("   ");
        assertEquals("Task 1", item.getTitle());
    }

    @Test
    void testSetDescription() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setDescription("New Description");
        assertEquals("New Description", item.getDescription());
        item.setDescription(null);
        assertNull(item.getDescription());
    }

    @Test
    void testSetDeadLineValid() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        LocalDate newDeadline = LocalDate.of(2026, 1, 1);
        item.setDeadLine(newDeadline);
        assertEquals(newDeadline, item.getDeadLine());
    }

    @Test
    void testSetDeadLineNullDoesNotUpdate() {
        LocalDate deadline = LocalDate.now();
        TodoItem item = new TodoItem("Task 1", deadline);
        item.setDeadLine(null);
        assertEquals(deadline, item.getDeadLine());
    }

    @Test
    void testSetDone() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setDone(true);
        assertTrue(item.getDone());
    }

    @Test
    void testSetCreatorValid() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        Person newCreator = new Person("Ben", "Bengtsson");
        item.setCreator(newCreator);
        assertEquals(newCreator, item.getCreator());
    }

    @Test
    void testSetCreatorNullDoesNotUpdate() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setCreator(creator);
        item.setCreator(null);
        assertEquals(creator, item.getCreator());
    }

    @Test
    void testIsOverdueTrue() {
        TodoItem item = new TodoItem("Task 1", LocalDate.of(2020, 1, 1));
        assertTrue(item.isOverdue());
    }

    @Test
    void testIsOverdueFalse() {
        TodoItem item = new TodoItem("Task 1", LocalDate.of(2026, 1, 1));
        assertFalse(item.isOverdue());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDate deadline = LocalDate.now();
        TodoItem item1 = new TodoItem("Task 1", "Desc", deadline, false, creator);
        TodoItem item2 = new TodoItem("Task 1", "Desc", deadline, false, creator);
        assertEquals(item1, item2);
        assertEquals(item1.hashCode(), item2.hashCode());
    }
}
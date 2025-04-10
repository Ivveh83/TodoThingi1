package model;

import org.junit.jupiter.api.Assertions;
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
        Assertions.assertEquals(1, item.getId());
        Assertions.assertEquals("Task 1", item.getTitle());
        Assertions.assertEquals("Description", item.getDescription());
        Assertions.assertEquals(deadline, item.getDeadLine());
        Assertions.assertFalse(item.getDone());
        Assertions.assertEquals(creator, item.getCreator());
    }

    @Test
    void testConstructorWithDefaultValues() {
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item = new TodoItem("Task 1", deadline);
        Assertions.assertEquals(1, item.getId());
        Assertions.assertEquals("Task 1", item.getTitle());
        assertNull(item.getDescription());
        Assertions.assertEquals(deadline, item.getDeadLine());
        Assertions.assertFalse(item.getDone());
        assertNull(item.getCreator());
    }

    @Test
    void testCounterIncrements() {
        LocalDate deadline = LocalDate.of(2025, 12, 31);
        TodoItem item1 = new TodoItem("Task 1", deadline);
        TodoItem item2 = new TodoItem("Task 2", deadline);
        Assertions.assertEquals(1, item1.getId());
        Assertions.assertEquals(2, item2.getId());
    }

    @Test
    void testSetTitleValid() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setTitle("New Task");
        Assertions.assertEquals("New Task", item.getTitle());
    }

    @Test
    void testSetTitleNullDoesNotUpdate() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setTitle(null);
        Assertions.assertEquals("Task 1", item.getTitle());
    }

    @Test
    void testSetTitleEmptyDoesNotUpdate() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setTitle("");
        Assertions.assertEquals("Task 1", item.getTitle());
    }

    @Test
    void testSetTitleBlankDoesNotUpdate() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setTitle("   ");
        Assertions.assertEquals("Task 1", item.getTitle());
    }

    @Test
    void testSetDescription() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setDescription("New Description");
        Assertions.assertEquals("New Description", item.getDescription());
        item.setDescription(null);
        assertNull(item.getDescription());
    }

    @Test
    void testSetDeadLineValid() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        LocalDate newDeadline = LocalDate.of(2026, 1, 1);
        item.setDeadLine(newDeadline);
        Assertions.assertEquals(newDeadline, item.getDeadLine());
    }

    @Test
    void testSetDeadLineNullDoesNotUpdate() {
        LocalDate deadline = LocalDate.now();
        TodoItem item = new TodoItem("Task 1", deadline);
        item.setDeadLine(null);
        Assertions.assertEquals(deadline, item.getDeadLine());
    }

    @Test
    void testSetDone() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setDone(true);
        Assertions.assertTrue(item.getDone());
    }

    @Test
    void testSetCreatorValid() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        Person newCreator = new Person("Ben", "Bengtsson");
        item.setCreator(newCreator);
        Assertions.assertEquals(newCreator, item.getCreator());
    }

    @Test
    void testSetCreatorNullDoesNotUpdate() {
        TodoItem item = new TodoItem("Task 1", LocalDate.now());
        item.setCreator(creator);
        item.setCreator(null);
        Assertions.assertEquals(creator, item.getCreator());
    }

    @Test
    void testIsOverdueTrue() {
        TodoItem item = new TodoItem("Task 1", LocalDate.of(2020, 1, 1));
        Assertions.assertTrue(item.isOverdue());
    }

    @Test
    void testIsOverdueFalse() {
        TodoItem item = new TodoItem("Task 1", LocalDate.of(2026, 1, 1));
        Assertions.assertFalse(item.isOverdue());
    }
}
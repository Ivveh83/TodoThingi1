import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskTest {

    private TodoItem todoItem;
    private Person assignee;

    @BeforeEach
    void setUp() {
        TodoItemTask.counter = 0;
        Person creator = new Person("Anna", "Andersson");
        todoItem = new TodoItem("Task 1", LocalDate.now());
        assignee = new Person("Ben", "Bengtsson");
    }

    @Test
    void testConstructorValidInput() {
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        assertEquals(1, task.getId());
        assertTrue(task.getAssigned());
        assertEquals(todoItem, task.getTodoItem());
        assertEquals(assignee, task.getAssignee());
    }

    @Test
    void testConstructorNullTodoItemDoesNotUpdate() {
        TodoItemTask task = new TodoItemTask(null, assignee);
        assertNull(task.getTodoItem());
        assertEquals(assignee, task.getAssignee());
        assertTrue(task.getAssigned());
    }

    @Test
    void testConstructorNullAssignee() {
        TodoItemTask task = new TodoItemTask(todoItem, null);
        assertEquals(1, task.getId());
        assertTrue(task.getAssigned()); // assigned blir true ändå pga setAssignee(null)
        assertEquals(todoItem, task.getTodoItem());
        assertNull(task.getAssignee());
    }

    @Test
    void testCounterIncrements() {
        TodoItemTask task1 = new TodoItemTask(todoItem, assignee);
        TodoItemTask task2 = new TodoItemTask(todoItem, null);
        assertEquals(1, task1.getId());
        assertEquals(2, task2.getId());
    }

    @Test
    void testSetAssigned() {
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        task.setAssigned(false);
        assertFalse(task.getAssigned());
        task.setAssigned(true);
        assertTrue(task.getAssigned());
    }

    @Test
    void testSetTodoItemValid() {
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        TodoItem newTodoItem = new TodoItem("Task 2", LocalDate.now());
        task.setTodoItem(newTodoItem);
        assertEquals(newTodoItem, task.getTodoItem());
    }

    @Test
    void testSetTodoItemNullDoesNotUpdate() {
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        task.setTodoItem(null);
        assertEquals(todoItem, task.getTodoItem());
    }

    @Test
    void testSetAssigneeValid() {
        TodoItemTask task = new TodoItemTask(todoItem, null);
        task.setAssignee(assignee);
        assertEquals(assignee, task.getAssignee());
        assertTrue(task.getAssigned());
    }

    @Test
    void testSetAssigneeNull() {
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        task.setAssignee(null);
        assertNull(task.getAssignee());
        assertTrue(task.getAssigned()); // assigned förblir true från konstruktorn
    }

    @Test
    void testEqualsAndHashCode() {
        TodoItemTask task1 = new TodoItemTask(todoItem, assignee);
        TodoItemTask task2 = new TodoItemTask(todoItem, assignee);
        assertEquals(task1, task2);
        assertEquals(task1.hashCode(), task2.hashCode());
    }
}
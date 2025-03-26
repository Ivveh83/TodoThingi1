import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskTest {

    private TodoItem todoItem;
    private Person assignee;

    @BeforeEach
    void setUp() {
        // Återställ counter och skapa standardinstanser före varje test
        TodoItemTask.counter = 0;
        Person creator = new Person("Anna", "Andersson", "anna@example.com");
        todoItem = new TodoItem("Task 1", "Do something", LocalDate.of(2025, 12, 31), false, creator);
        assignee = new Person("Ben", "Bengtsson", "ben@example.com");
    }

    @Test
    void testConstructorValidTodoItemAndAssignee() {
        // Testar konstruktorn med giltigt TodoItem och Assignee
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        assertEquals(1, task.getId(), "ID ska vara 1 för första instansen");
        assertTrue(task.getAssigned(), "Assigned ska vara true när assignee anges");
        assertEquals(todoItem, task.getTodoItem(), "TodoItem ska matcha");
        assertEquals(assignee, task.getAssignee(), "Assignee ska matcha");
    }

    @Test
    void testConstructorNullTodoItemThrowsException() {
        // Testar att null TodoItem kastar undantag
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TodoItemTask(null, assignee);
        });
        assertEquals("Must not be null", exception.getMessage(), "Felmeddelande ska matcha");
    }

    @Test
    void testConstructorNullAssigneeSetsAssignedFalse() {
        // Testar att null Assignee sätter assigned till false
        TodoItemTask task = new TodoItemTask(todoItem, null);
        assertEquals(1, task.getId(), "ID ska vara 1 för första instansen");
        assertFalse(task.getAssigned(), "Assigned ska vara false när assignee är null");
        assertEquals(todoItem, task.getTodoItem(), "TodoItem ska matcha");
        assertNull(task.getAssignee(), "Assignee ska vara null");
    }

    @Test
    void testCounterIncrements() {
        // Testar att counter ökar för varje ny instans
        TodoItemTask task1 = new TodoItemTask(todoItem, assignee);
        TodoItemTask task2 = new TodoItemTask(todoItem, null);
        assertEquals(1, task1.getId(), "Första task-ID ska vara 1");
        assertEquals(2, task2.getId(), "Andra task-ID ska vara 2");
    }

    @Test
    void testSetAssigned() {
        // Testar att setAssigned fungerar
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        task.setAssigned(false);
        assertFalse(task.getAssigned(), "Assigned ska vara false efter uppdatering");
        task.setAssigned(true);
        assertTrue(task.getAssigned(), "Assigned ska vara true efter uppdatering");
    }

    @Test
    void testSetTodoItemValid() {
        // Testar att setTodoItem fungerar med giltigt värde
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        TodoItem newTodoItem = new TodoItem("Task 2", "Do another thing", LocalDate.of(2026, 1, 1), true, assignee);
        task.setTodoItem(newTodoItem);
        assertEquals(newTodoItem, task.getTodoItem(), "TodoItem ska uppdateras");
    }

    @Test
    void testSetTodoItemNullDoesNotUpdate() {
        // Testar att setTodoItem inte uppdaterar vid null
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        task.setTodoItem(null);
        assertEquals(todoItem, task.getTodoItem(), "TodoItem ska inte ändras vid null");
    }

    @Test
    void testSetAssigneeValid() {
        // Testar att setAssignee fungerar med giltigt värde
        TodoItemTask task = new TodoItemTask(todoItem, null);
        task.setAssignee(assignee);
        assertEquals(assignee, task.getAssignee(), "Assignee ska uppdateras");
    }

    @Test
    void testSetAssigneeNull() {
        // Testar att setAssignee accepterar null
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        task.setAssignee(null);
        assertNull(task.getAssignee(), "Assignee ska vara null efter uppdatering");
    }
}
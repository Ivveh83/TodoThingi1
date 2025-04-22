package collections;

import model.Person;
import model.TodoItem;
import model.TodoItemTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskDAOCollectionTest {

    // Declaring states
    TodoItemTaskDAOCollection todoItemTaskDAOCollection = new TodoItemTaskDAOCollection();
    TodoItemTask todoItemTask;
    Person testPerson;
    TodoItem todoItem;
    PersonDAOCollection personDAOCollection = new PersonDAOCollection();

    @BeforeEach
    void setUp() {
        testPerson = new Person("Kalle", "Anka", "kalle@anka.com");
        todoItem = new TodoItem("Test Title", "", LocalDate.now().plusDays(1), false, testPerson);
        personDAOCollection.persist(testPerson);
    }

    @Test
    void persistAssignsIdAndAddsToCollectionSuccessfully() {
        // Scenario
        todoItemTask = new TodoItemTask(todoItem, testPerson);

        // Expected
        TodoItemTask result = todoItemTaskDAOCollection.persist(todoItemTask);

        // Verify
        assertEquals(1, result.getId());
        assertEquals(1, todoItemTaskDAOCollection.findAll().size());
        assertSame(result, todoItemTaskDAOCollection.findById(1));
    }

    @Test
    void testFindById() {
        // Scenario
        todoItemTask = new TodoItemTask(todoItem, testPerson);
        TodoItemTask storedTodoItemTask = todoItemTaskDAOCollection.persist(todoItemTask);

        // Expected
        TodoItemTask found = todoItemTaskDAOCollection.findById(storedTodoItemTask.getId());

        // Verify
        assertNotNull(found);
        assertEquals("Test Title", found.getTodoItem().getTitle());
    }

    @Test
    void testFindByIdNonExistent() {
        // Scenario: Attempt to find a TodoItemTask with a non-existent ID
        int nonExistentId = 999;

        // Expected
        TodoItemTask found = todoItemTaskDAOCollection.findById(nonExistentId);

        // Verify
        assertNull(found);
    }

    @Test
    void testFindByAssignedStatus() {
        // Scenario
        todoItemTask = new TodoItemTask(todoItem, testPerson);
        TodoItemTask storedTodoItemTaskWithStatusTrue = todoItemTaskDAOCollection.persist(todoItemTask);

        TodoItem todoItem2 = new TodoItem("Test Title 2", "", LocalDate.now().plusDays(1), false, testPerson);
        TodoItemTask todoItemTask2 = new TodoItemTask(todoItem2, null); // Unassigned task
        TodoItemTask storedTodoItemTaskWithStatusFalse = todoItemTaskDAOCollection.persist(todoItemTask2);
        storedTodoItemTaskWithStatusFalse.setAssigned(false);

        // Expected
        ArrayList<TodoItemTask> foundTrue = todoItemTaskDAOCollection.findByAssignedStatus(true);
        ArrayList<TodoItemTask> foundFalse = todoItemTaskDAOCollection.findByAssignedStatus(false);

        // Verify
        assertNotNull(foundTrue);
        assertNotNull(foundFalse);
        assertEquals(1, foundTrue.size());
        assertEquals(1, foundFalse.size());
        assertEquals(storedTodoItemTaskWithStatusTrue, foundTrue.get(0));
        assertEquals(storedTodoItemTaskWithStatusFalse, foundFalse.get(0));
        assertEquals(2, todoItemTaskDAOCollection.findAll().size());
    }

    @Test
    void testFindByPersonId() {
        // Scenario
        Person person2 = new Person("Musse", "Pigg", "musse@pigg.com");
        personDAOCollection.persist(person2);
        todoItemTask = new TodoItemTask(todoItem, testPerson);
        TodoItemTask storedTodoItemTask1 = todoItemTaskDAOCollection.persist(todoItemTask);

        TodoItem todoItem2 = new TodoItem("Test Title 2", "", LocalDate.now().plusDays(1), false, person2);
        TodoItemTask todoItemTask2 = new TodoItemTask(todoItem2, person2);
        TodoItemTask storedTodoItemTask2 = todoItemTaskDAOCollection.persist(todoItemTask2);

        // Expected
        ArrayList<TodoItemTask> foundForPerson1 = todoItemTaskDAOCollection.findByPersonId(testPerson.getId());
        ArrayList<TodoItemTask> foundForPerson2 = todoItemTaskDAOCollection.findByPersonId(person2.getId());

        // Verify
        assertNotNull(foundForPerson1);
        assertNotNull(foundForPerson2);
        assertEquals(1, foundForPerson1.size());
        assertEquals(1, foundForPerson2.size());
        assertEquals(storedTodoItemTask1, foundForPerson1.get(0));
        assertEquals(storedTodoItemTask2, foundForPerson2.get(0));
    }

    @Test
    void testFindByPersonIdNonExistent() {
        // Scenario: Attempt to find TodoItemTasks for a non-existent person ID
        todoItemTask = new TodoItemTask(todoItem, testPerson);
        todoItemTaskDAOCollection.persist(todoItemTask);

        int nonExistentPersonId = 999;

        // Expected
        ArrayList<TodoItemTask> found = todoItemTaskDAOCollection.findByPersonId(nonExistentPersonId);

        // Verify
        assertNotNull(found);
        assertTrue(found.isEmpty());
    }

    @Test
    void testRemove() {
        // Scenario
        todoItemTask = new TodoItemTask(todoItem, testPerson);
        TodoItemTask storedTodoItemTask = todoItemTaskDAOCollection.persist(todoItemTask);

        // Expected
        todoItemTaskDAOCollection.remove(storedTodoItemTask.getId());

        // Verify
        assertEquals(0, todoItemTaskDAOCollection.findAll().size());
        assertNull(todoItemTaskDAOCollection.findById(storedTodoItemTask.getId()));
    }

    @Test
    void testRemoveNonExistent() {
        // Scenario: Attempt to remove a TodoItemTask with a non-existent ID
        int nonExistentId = 999;

        // Expected
        todoItemTaskDAOCollection.remove(nonExistentId);

        // Verify
        assertEquals(0, todoItemTaskDAOCollection.findAll().size());
    }

    @Test
    void testFindAllEmptyCollection() {
        // Scenario: Check findAll when the collection is empty
        ArrayList<TodoItemTask> found = todoItemTaskDAOCollection.findAll();

        // Verify
        assertNotNull(found);
        assertTrue(found.isEmpty());
    }
}
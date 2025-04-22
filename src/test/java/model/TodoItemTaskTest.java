package model;

import DAO.TodoItemTaskDAO;
import collections.TodoItemTaskDAOCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sequenzers.TodoItemIdSequencer;
import sequenzers.TodoItemTaskIdSequencer;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTaskTest {

    private TodoItem todoItem;
    private Person assignee;

    @BeforeEach
    void setUp() {
        TodoItemTaskIdSequencer.setCurrentId(0);
        Person creator = new Person("Anna", "Andersson");
        todoItem = new TodoItem("Task 1", LocalDate.now());
        assignee = new Person("Ben", "Bengtsson");
    }

    @Test
    void testConstructorValidInput() {
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        Assertions.assertEquals(0, task.getId());
        Assertions.assertTrue(task.getAssigned());
        Assertions.assertEquals(todoItem, task.getTodoItem());
        Assertions.assertEquals(assignee, task.getAssignee());
    }

    @Test
    void testConstructorNullTodoItemDoesNotUpdate() {
        TodoItemTask task = new TodoItemTask(null, assignee);
        assertNull(task.getTodoItem());
        Assertions.assertEquals(assignee, task.getAssignee());
        Assertions.assertTrue(task.getAssigned());
    }

    @Test
    void testConstructorNullAssignee() {
        TodoItemTask task = new TodoItemTask(todoItem, null);
        Assertions.assertEquals(0, task.getId());
        Assertions.assertTrue(task.getAssigned()); // assigned blir true ändå pga setAssignee(null)
        Assertions.assertEquals(todoItem, task.getTodoItem());
        assertNull(task.getAssignee());
    }

    @Test
    void testCounterIncrements() {
        TodoItemTask task1 = new TodoItemTask(todoItem, assignee);
        TodoItemTask task2 = new TodoItemTask(todoItem, null);
        TodoItemTaskDAOCollection collection = new TodoItemTaskDAOCollection();
        TodoItemTask registeredTask1 = collection.persist(task1);
        TodoItemTask registeredTask2 = collection.persist(task2);
        Assertions.assertEquals(1, registeredTask1.getId());
        Assertions.assertEquals(2, registeredTask2.getId());
    }

    @Test
    void testSetAssigned() {
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        task.setAssigned(false);
        Assertions.assertFalse(task.getAssigned());
        task.setAssigned(true);
        Assertions.assertTrue(task.getAssigned());
    }

    @Test
    void testSetTodoItemValid() {
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        TodoItem newTodoItem = new TodoItem("Task 2", LocalDate.now());
        task.setTodoItem(newTodoItem);
        Assertions.assertEquals(newTodoItem, task.getTodoItem());
    }

    @Test
    void testSetTodoItemNullDoesNotUpdate() {
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        task.setTodoItem(null);
        Assertions.assertEquals(todoItem, task.getTodoItem());
    }

    @Test
    void testSetAssigneeValid() {
        TodoItemTask task = new TodoItemTask(todoItem, null);
        task.setAssignee(assignee);
        Assertions.assertEquals(assignee, task.getAssignee());
        Assertions.assertTrue(task.getAssigned());
    }

    @Test
    void testSetAssigneeNull() {
        TodoItemTask task = new TodoItemTask(todoItem, assignee);
        task.setAssignee(null);
        assertNull(task.getAssignee());
        Assertions.assertTrue(task.getAssigned()); // assigned förblir true från konstruktorn
    }
}
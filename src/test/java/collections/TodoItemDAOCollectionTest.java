package collections;

import model.Person;
import model.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sequenzers.TodoItemIdSequencer;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemDAOCollectionTest {

    private TodoItemDAOCollection dao;
    private Person testPerson;

    @BeforeEach
    void setUp() {
        dao = new TodoItemDAOCollection();
        TodoItemIdSequencer.setCurrentId(0);
        testPerson = new Person("Kalle", "Anka", "kalle@anka.com");
    }

    @Test
    void testPersist() {
        TodoItem item = new TodoItem("Test Title", "", LocalDate.now().plusDays(1), false, testPerson);
        TodoItem result = dao.persist(item);

        assertEquals(1, result.getId());
        assertEquals(1, dao.findAll().size());
        assertSame(result, dao.findById(1));
    }

    @Test
    void testFindById() {
        dao.persist(new TodoItem("One", "Desc", LocalDate.now(), false, testPerson));
        TodoItem item2 = dao.persist(new TodoItem("Two", "Desc", LocalDate.now(), true, testPerson));

        TodoItem found = dao.findById(item2.getId());

        assertNotNull(found);
        assertEquals("Two", found.getTitle());
    }

    @Test
    void testFindAllByDoneStatus() {
        dao.persist(new TodoItem("Task 1", "", LocalDate.now(), true, testPerson));
        dao.persist(new TodoItem("Task 2", "", LocalDate.now(), false, testPerson));

        List<TodoItem> doneTasks = dao.findAllByDoneStatus(true);
        assertEquals(1, doneTasks.size());
        assertTrue(doneTasks.get(0).getDone());
    }

    @Test
    void testFindByTitleContainsExactMatchOnly() {
        dao.persist(new TodoItem( "Meeting", "", LocalDate.now(), false, testPerson));
        dao.persist(new TodoItem( "meeting", "", LocalDate.now(), false, testPerson));

        List<TodoItem> found = dao.findByTitleContains("Meeting");
        assertEquals(1, found.size());
        assertEquals("Meeting", found.get(0).getTitle());
    }

    @Test
    void testFindByPersonIdMatchesIncorrectly() {
        TodoItem item = new TodoItem("Test", "", LocalDate.now(), false, testPerson);
        dao.persist(item);

        List<TodoItem> result = dao.findByPersonId(testPerson.getId() + 1);
        assertEquals(0, result.size());
    }

    @Test
    void testFindByDeadlineBefore() {
        LocalDate future = LocalDate.now().plusDays(5);
        LocalDate past = LocalDate.now().minusDays(1);

        dao.persist(new TodoItem("Old", "", past, false, testPerson));
        dao.persist(new TodoItem("Future", "", future, false, testPerson));

        List<TodoItem> beforeNow = dao.findByDeadlineBefore(LocalDate.now());
        assertEquals(1, beforeNow.size());
        assertEquals("Old", beforeNow.get(0).getTitle());
    }

    @Test
    void testFindByDeadlineAfter() {
        LocalDate future = LocalDate.now().plusDays(3);

        dao.persist(new TodoItem("Upcoming", "", future, false, testPerson));

        List<TodoItem> result = dao.findByDeadlineAfter(LocalDate.now());
        assertEquals(1, result.size());
        assertEquals("Upcoming", result.get(0).getTitle());
    }

    @Test
    void testRemoveDeletesCorrectItem() {
        TodoItem item = dao.persist(new TodoItem("ToRemove", "", LocalDate.now(), false, testPerson));
        int id = item.getId();

        dao.remove(id);

        assertNull(dao.findById(id));
        assertEquals(0, dao.findAll().size());
    }
}

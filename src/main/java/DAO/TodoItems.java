package DAO;

import model.Person;
import model.Todo;
import java.util.Collection;

public interface TodoItems {
    Todo create(Todo todo);
    Collection<Todo> findAll();
    Todo findById(int id);
    Collection<Todo> findByDoneStatus(boolean done);
    Collection<Todo> findByAssignee(int id);
    Collection<Todo> findByAssignee(Person person);
    Collection<Todo> findByUnassignedTodoItems();
    Todo update(Todo todo);
    boolean deleteById(int id);
}

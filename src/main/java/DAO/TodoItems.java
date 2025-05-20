package DAO;

import model.Person;
import model.Todo;
import java.util.Collection;

public interface TodoItems extends Base<Todo> {
    Collection<Todo> findByDoneStatus(boolean done);
    Collection<Todo> findByAssignee(int id);
    Collection<Todo> findByAssignee(Person person);
    Collection<Todo> findByUnassignedTodoItems();
}

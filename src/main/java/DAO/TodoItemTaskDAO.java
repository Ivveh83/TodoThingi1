package DAO;

import model.TodoItemTask;

import java.util.ArrayList;

public interface TodoItemTaskDAO {
    TodoItemTask persist(TodoItemTask todoItemTask);
    TodoItemTask findById(int id);
    ArrayList<TodoItemTask> findAll();
    ArrayList<TodoItemTask> findByAssignedStatus(boolean status);
    ArrayList<TodoItemTask> findByPersonId(int personId);
    void remove(int id);
}

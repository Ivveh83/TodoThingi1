package DAO;

import model.TodoItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface TodoItemDAO {
    TodoItem persist(TodoItem todoItem);
    TodoItem findById(int id);
    ArrayList<TodoItem> findAll();
    ArrayList<TodoItem> findAllByDoneStatus(boolean done);
    ArrayList<TodoItem> findByTitleContains(String title);
    ArrayList<TodoItem> findByPersonId(int personId);
    ArrayList<TodoItem> findByDeadlineBefore(LocalDate date);
    ArrayList<TodoItem> findByDeadlineAfter(LocalDate date);
    void remove(int id);
}

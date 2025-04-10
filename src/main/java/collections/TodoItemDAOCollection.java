package collections;

import model.Person;
import model.TodoItem;
import model.TodoItemTask;
import sequenzers.TodoItemIdSequencer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class TodoItemDAOCollection {
    ArrayList<TodoItem> todoItemArrayList = new ArrayList<>();

    public TodoItem persist(TodoItem todoItem) {
            todoItem.setId(TodoItemIdSequencer.nextId());
            todoItemArrayList.add(todoItem);
            return todoItem;
    }

    public TodoItem findById(int id) {
        for (TodoItem todoItem : todoItemArrayList){
            if (todoItem.getId() == id) return todoItem;
        }
        System.out.println("No such id does exist");
        return null;
    }

    public ArrayList<TodoItem> findAll() {
        return todoItemArrayList;
    }

    public ArrayList<TodoItem> findAllByDoneStatus(boolean done) {
        ArrayList<TodoItem> arrayList = new ArrayList<>();
        for (TodoItem todoItem : todoItemArrayList){
            if (todoItem.getDone() == done) arrayList.add(todoItem);
        }
        return arrayList;
    }

    public ArrayList<TodoItem> findByTitleContains(String title) {
        ArrayList<TodoItem> arrayList = new ArrayList<>();
        for (TodoItem todoItem : todoItemArrayList){
            if (todoItem.getTitle() == title) arrayList.add(todoItem);
        }
        return arrayList;
    }

    public ArrayList<TodoItem> findByPersonId(int personId) {
        ArrayList<TodoItem> arrayList = new ArrayList<>();
        for (TodoItem todoItem : todoItemArrayList){
            if (todoItem.getId() == personId) arrayList.add(todoItem);
        }
        return arrayList;
    }

    public ArrayList<TodoItem> findByDeadlineBefore(LocalDate date) {
        ArrayList<TodoItem> arrayList = new ArrayList<>();
        for (TodoItem todoItem : todoItemArrayList){
            if (todoItem.getDeadLine().isBefore(date)) arrayList.add(todoItem);
        }
        return arrayList;
    }

    public ArrayList<TodoItem> findByDeadlineAfter(LocalDate date) {
        ArrayList<TodoItem> arrayList = new ArrayList<>();
        for (TodoItem todoItem : todoItemArrayList){
            if (todoItem.getDeadLine().isAfter(date)) arrayList.add(todoItem);
        }
        return arrayList;
    }

    public void remove(int id) {
        boolean objectRemoved = false;
        Iterator itr = todoItemArrayList.iterator();
        while (itr.hasNext()){
            TodoItem todoItem = (TodoItem) itr.next();
            if (todoItem.getId() == id){
                itr.remove();
                System.out.println("Removed object sucessfully");
                objectRemoved = true;
            }
        }
        if (!objectRemoved) System.out.println("Object not found");
    }
}
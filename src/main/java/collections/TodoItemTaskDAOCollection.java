package collections;

import model.TodoItemTask;
import sequenzers.TodoItemIdSequencer;

import java.util.ArrayList;
import java.util.Iterator;

public class TodoItemTaskDAOCollection {

    ArrayList<TodoItemTask> todoItemTaskArrayList = new ArrayList<>();

    public TodoItemTask persist(TodoItemTask todoItemTask) {
        todoItemTask.setId(TodoItemIdSequencer.nextId());
        todoItemTaskArrayList.add(todoItemTask);
        return todoItemTask;
    }

    public TodoItemTask findById(int id) {
        for (TodoItemTask todoItemTask : todoItemTaskArrayList){
            if (todoItemTask.getId() == id) return todoItemTask;
        }
        System.out.println("No such id does exist");
        return null;
    }

    public ArrayList<TodoItemTask> findAll() {
        return todoItemTaskArrayList;
    }

    public ArrayList<TodoItemTask> findByAssignedStatus(boolean status) {
        ArrayList<TodoItemTask> todoItemTasks = new ArrayList<>();
        for (TodoItemTask todoItemTask : todoItemTaskArrayList){
            if (todoItemTask.getAssigned() == status) todoItemTasks.add(todoItemTask);
        }
        return todoItemTasks;
    }

    public ArrayList<TodoItemTask> findByPersonId(int personId) {
        ArrayList<TodoItemTask> todoItemTasks = new ArrayList<>();
        for (TodoItemTask todoItemTask : todoItemTaskArrayList){
            if (todoItemTask.getId() == personId) todoItemTasks.add(todoItemTask);
        }
        return todoItemTasks;
    }

    public void remove(int id) {
        boolean objectRemoved = false;
        Iterator itr = todoItemTaskArrayList.iterator();
        while (itr.hasNext()){
            TodoItemTask todoItemTask = (TodoItemTask) itr.next();
            if (todoItemTask.getId() == id){
                itr.remove();
                System.out.println("Removed object sucessfully");
                objectRemoved = true;
            }
        }
        if (!objectRemoved) System.out.println("Object not found");
    }
}

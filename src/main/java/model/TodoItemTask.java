package model;

import java.util.Objects;

public class TodoItemTask {

//    static int counter;
    private int id;
    private boolean assigned;
    private Todo todo;
    private Person assignee;

    public TodoItemTask(Todo todo, Person assignee){
//        this.id = ++counter;
        setTodoItem(todo);
        setAssignee(assignee);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned){
        this.assigned = assigned;
    }

    public Todo getTodoItem() {
        return todo;
    }

    public void setTodoItem(Todo todo) {
        if (todo == null){
            System.out.println("Must not be null");
        }else {
            this.todo = todo;
        }
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
        this.assigned = true;
    }

    @Override
    public String toString() {
        return "TodoItemTask{" +
                "id=" + id +
                ", assigned=" + assigned +
                ", todoItem=" + todo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemTask that = (TodoItemTask) o;
        return id == that.id && assigned == that.assigned && Objects.equals(todo, that.todo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assigned, todo);
    }
}

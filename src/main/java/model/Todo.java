package model;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {

//    static int counter;
    private int todoId;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person assignee;

    //Constructor for creating a db post
    public Todo(String title, String description, LocalDate deadLine, Person assignee){
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDone(false);
        setAssignee(assignee);
    }

    //Constructor for reading a db post
    public Todo(int todoId, String title, String description, LocalDate deadLine, boolean done, Person assignee){
        setTodoId(todoId);
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDone(done);
        setAssignee(assignee);
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            System.out.println("Must not be null");
        } else if (title.isBlank()) {
            System.out.println("Must not contain only spaces");
        } else if (title.isEmpty()) {
            System.out.println("Must not be empty");
        } else {
            this.title = title;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        if (deadLine == null) {
            System.out.println("Must not be null");
        }else {
            this.deadLine = deadLine;
        }
    }

    public boolean getDone(){
        return this.done;
    }

    public void setDone(boolean done){
        this.done = done;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }


    @Override
    public String toString() {
        return "Todo{" +
                "todo_id=" + todoId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                ", assignee=" + assignee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return todoId == todo.todoId && Objects.equals(title, todo.title)
                && Objects.equals(description, todo.description) && Objects.equals(deadLine, todo.deadLine)
                && done == todo.done && Objects.equals(assignee, todo.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todoId, title, description, deadLine, done, assignee);
    }

    public boolean isOverdue(){
        LocalDate today = LocalDate.now();
        if (today.isAfter(this.deadLine)){
            return true;
        }
        return false;
    }
}
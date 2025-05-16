package model;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {

//    static int counter;
    private int todo_id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Integer assignee_id;

    //Constructor for creating a db post
    public Todo(String title, String description, LocalDate deadLine, Integer assignee_id){
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDone(false);
        setAssignee_id(assignee_id);
    }

    //Constructor for reading a db post
    public Todo(int todo_id, String title, String description, LocalDate deadLine, boolean done, Integer assignee_id){
        setTodo_id(todo_id);
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDone(done);
        setAssignee_id(assignee_id);
    }

    public int getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(int todo_id) {
        this.todo_id = todo_id;
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

    public Integer getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(Integer assignee_id) {
        this.assignee_id = assignee_id;
    }


    @Override
    public String toString() {
        return "Todo{" +
                "todo_id=" + todo_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", isDone=" + done +
                ", assignee_id=" + assignee_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return todo_id == todo.todo_id && Objects.equals(title, todo.title)
                && Objects.equals(description, todo.description) && Objects.equals(deadLine, todo.deadLine)
                && done == todo.done && Objects.equals(assignee_id, todo.assignee_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todo_id, title, description, deadLine, done, assignee_id);
    }

    public boolean isOverdue(){
        LocalDate today = LocalDate.now();
        if (today.isAfter(this.deadLine)){
            return true;
        }
        return false;
    }
}
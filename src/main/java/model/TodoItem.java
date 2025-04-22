package model;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {

//    static int counter;
    private int id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;

    public TodoItem(String title, String description, LocalDate deadLine, boolean done, Person creator){
//        this.id = ++counter;
        setTitle(title);
        this.description = description;
        setDeadLine(deadLine);
        this.done = done;
        this.creator = creator;
    }

    TodoItem(String title, LocalDate deadLine){
        this(title, null, deadLine, false, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        if (creator == null) {
            System.out.println("Must not be null");
        }else {
            this.creator = creator;
        }
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return id == todoItem.id && done == todoItem.done && Objects.equals(title, todoItem.title)
                && Objects.equals(description, todoItem.description) && Objects.equals(deadLine, todoItem.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, deadLine, done);
    }

    public boolean isOverdue(){
        LocalDate today = LocalDate.now();
        if (today.isAfter(this.deadLine)){
            return true;
        }
        return false;
    }
}


//System.out.println("title: " + getTitle() +
//                ", description: " + getDescription() +
//                ", deadLine: " + getDeadLine() +
//                ", done: " + getDone() +
//                ", creator: " + firstName + " " + lastName);
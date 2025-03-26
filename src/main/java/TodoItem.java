import java.time.LocalDate;

public class TodoItem {

    static int counter;
    private int id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;

    TodoItem(String title, String description, LocalDate deadLine, boolean done, Person creator){
        this.id = ++counter;
        if (title == null){
            throw new IllegalArgumentException("Must not be null");
        }else if (title.isEmpty()) {
            throw new IllegalArgumentException("Must not be empty");
        } else if (title.isBlank()) {
            throw new IllegalArgumentException("Must not contain of only spaces");
        }
        this.title = title;
        this.description = description;
        if (deadLine == null){
            throw new IllegalArgumentException("Must not be null");
        }
        this.deadLine = deadLine;
        this.done = done;
        this.creator = creator;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            System.out.println("Must not be null");
        } else if (title.isBlank()) {
            System.out.println("Must not contain of only spaces");
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

    public String getSummary(){
        String firstName;
        String lastName;
        try {
            firstName = getCreator().getFirstName();
            lastName = getCreator().getLastName();
        }catch (NullPointerException e){
            firstName = "n/a";
            lastName = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("title: " + getTitle())
                        .append(", description: " + getDescription())
                        .append(", deadLine: " + getDeadLine())
                        .append(", done: " + getDone())
                        .append(", creator: " + firstName + " " + lastName);

        return sb.toString();
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
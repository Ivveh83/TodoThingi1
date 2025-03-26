public class TodoItemTask {

    static int counter;
    private int id;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee;

    TodoItemTask(TodoItem todoItem, Person assignee){
        this.id = ++counter;
        setTodoItem(todoItem);
        setAssignee(assignee);
    }

    public int getId() {
        return id;
    }

    public boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned){
        this.assigned = assigned;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        if (todoItem == null){
            System.out.println("Must not be null");
        }else {
            this.todoItem = todoItem;
        }
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
        this.assigned = true;
    }

    public String getSummary(){
        String firstName;
        String lastName;
        try {
            firstName = this.getAssignee().getFirstName();
            lastName = this.getAssignee().getLastName();
        }catch (NullPointerException e){
            firstName = "n/a";
            lastName = "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("id: " + this.getId())
                        .append(", assigned: " + this.getAssigned() )
                        .append(", todoItem: " + this.getTodoItem().getTitle() )
                        .append(", assignee: " + firstName + " " + lastName);

        return sb.toString();
    }
}

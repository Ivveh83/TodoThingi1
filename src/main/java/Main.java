import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Kalle", "Anka", "kalle@anka.com");
        Person person2 = new Person("Joakim", "von Anka");

        System.out.println(person1);
        System.out.println(person2);

        person2.setEmail("jv@anka.com");
        System.out.println(person2.getEmail());

        LocalDate date = LocalDate.of(2025, 03, 26);
        TodoItem todoItem1 = new TodoItem("Do the dishes", "n/a", date, false, person2);
        todoItem1.setDone(true);
        System.out.println(todoItem1);
        System.out.println("Deadline overdue: " + todoItem1.isOverdue());

        TodoItemTask todoItemTask1 = new TodoItemTask(todoItem1, person1);
        System.out.println(todoItemTask1);

        AppUser appUser = new AppUser("Gizmo-quack", "qwerty", AppRole.ROLE_APP_USER);
        System.out.println(appUser);
        System.out.println(appUser.hashCode());
        System.out.println(new Object().equals(appUser));
        System.out.println(appUser.equals(appUser));
    }
}

//StringBuilder sb = new StringBuilder(); .append(), String result = sb.toString();
//UML (Unified Modelling Language)
//Multiplicity (0..1), how many instances of one Class there can be in another Class
//One Directional (0..1) or MultiDirectional (0..*)

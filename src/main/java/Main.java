import DAO.PersonDAO;
import collections.PersonDAOCollection;
import collections.TodoItemDAOCollection;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Person person1 = new Person("Kalle", "Anka", "kalle@anka.com"); // 0
        Person person2 = new Person("Joakim", "von Anka", "jv@anka.com");
        /*
        System.out.println("person1 = " + person1);
        PersonDAOCollection inMemoryStorageForPerson = new PersonDAOCollection();

        Person registeredPerson1 = inMemoryStorageForPerson.persist(person1); // Id: 1
        Person registeredPerson2 = inMemoryStorageForPerson.persist(person2); // Id: 2
        System.out.println("You registration is done successfully! Here is your ID: " + registeredPerson1);
        ArrayList<Person> personArrayList = inMemoryStorageForPerson.findAll();
        System.out.println(personArrayList);
        System.out.println(inMemoryStorageForPerson.findById(1));
        System.out.println(inMemoryStorageForPerson.findByEmail("kalle@anka.com"));
//        inMemoryStorageForPerson.remove(1);
//        System.out.println(inMemoryStorageForPerson.findAll());
*/



        LocalDate date = LocalDate.of(2025, 03, 26);
        TodoItem todoItem1 = new TodoItem("Do the dishes", "n/a", date, false, person2);
        todoItem1.setDone(true);
        System.out.println(todoItem1);
        System.out.println("Deadline overdue: " + todoItem1.isOverdue());

        TodoItemTask todoItemTask1 = new TodoItemTask(todoItem1, person1);
        System.out.println("assigned: " + todoItemTask1.getAssigned());

        AppUser appUser = new AppUser("Gizmo-quack", "qwerty", AppRole.ROLE_APP_USER);
        System.out.println(appUser);
        System.out.println(appUser.hashCode());
        System.out.println(new Object().equals(appUser));
        System.out.println(appUser.equals(appUser));

        System.out.println("---------------------------------");
        TodoItemDAOCollection inMemoryStorageForTodoItem = new TodoItemDAOCollection();
        TodoItem todoItemRegistered1 = inMemoryStorageForTodoItem.persist(todoItem1);
        System.out.println(todoItemRegistered1);
        System.out.println(inMemoryStorageForTodoItem.findAllByDoneStatus(false));
        LocalDate dateBefore = LocalDate.of(2025, 03, 25);
        System.out.println(inMemoryStorageForTodoItem.findByDeadlineAfter(dateBefore));



    }
}

//StringBuilder sb = new StringBuilder(); .append(), String result = sb.toString();
//UML (Unified Modelling Language)
//Multiplicity (0..1), how many instances of one Class there can be in another Class
//One Directional (0..1) or MultiDirectional (0..*)

import DAO.People;
import DAO.TodoItems;
import DAO.impl.PeopleImpl;
import DAO.impl.TodoItemsImpl;
import db.MySQLConnection;
import model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        printByAssigneeId();

    }

    public static void addPerson() {
        try (Scanner scanner = new Scanner(System.in)) {

            People people = new PeopleImpl(MySQLConnection.getConnection());
            System.out.println("Enter first name: ");
            String first_name = scanner.nextLine();
            System.out.println("Enter last name: ");
            String last_name = scanner.nextLine();

            Person person = new Person(first_name, last_name);
            Person savedPerson = people.create(person);
            System.out.println("Saved person: " + savedPerson);
            System.out.println("Operation is Done!");

        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void printAllPersons() {
        try {
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());
            Collection<Person> personArrayList = people.findAll();
            for (Person person : personArrayList)
                System.out.println(person);

        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void printPersonById() {
        try (Scanner scanner = new Scanner(System.in)) {

            People people = new PeopleImpl(MySQLConnection.getConnection());
            System.out.println("Enter ID#: ");
            int id = scanner.nextInt();
            System.out.println(people.findById(id));
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        } catch (InputMismatchException e) {
            System.out.println("Must enter a valid number");
        }
    }

    public static void printByName() {
        try (Scanner scanner = new Scanner(System.in)) {
            People people = new PeopleImpl(MySQLConnection.getConnection());
            System.out.println("Enter Name: ");
            String name = scanner.nextLine();
            Collection<Person> peopleByName = people.findByName(name);
            if (!peopleByName.isEmpty()) {
                for (Person person : peopleByName) {
                    System.out.println(person);
                }
            } else System.out.println("No person with that name exists");
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void printUpdatedPerson() {
        try (Scanner scanner = new Scanner(System.in)) {
            People people = new PeopleImpl(MySQLConnection.getConnection());

            System.out.println("Enter ID#: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Person formerPerson = people.findById(id);

            if (formerPerson != null) {
                System.out.println("Enter new first name: ");
                String newFirstName = scanner.nextLine();
                System.out.println("Enter new last name: ");
                String newLastName = scanner.nextLine();
                Person newPerson = new Person(formerPerson.getPerson_id(), newFirstName, newLastName);
                System.out.println(people.update(newPerson));
            } else System.out.println("No such ID");

        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void deletePerson() {
        try (Scanner scanner = new Scanner(System.in)) {
            People people = new PeopleImpl(MySQLConnection.getConnection());

            System.out.println("Enter ID#: ");
            int id = scanner.nextInt();

            if (people.deleteById(id)) System.out.println("Person deleted successfully");
            else System.out.println("Person not deleted");
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void addTodo() {
        try (Scanner scanner = new Scanner(System.in)) {

            TodoItems todoItems = new TodoItemsImpl(MySQLConnection.getConnection());
            System.out.println("Enter title: ");
            String title = scanner.nextLine();
            System.out.println("Enter description: ");
            String description = scanner.nextLine();
            System.out.println("Enter deadline in format \"YYYY-MM-DD\": ");
            String deadlineAsString = scanner.nextLine();
            LocalDate deadline = LocalDate.parse(deadlineAsString);
            System.out.println("Enter Assignee Id: ");
            Integer assignee_id = scanner.nextInt();
            scanner.nextLine();

            // Controlling that a matching person_id do exist
            People people = new PeopleImpl(MySQLConnection.getConnection());
            Collection<Person> personCollection = people.findAll();
            boolean assigneeIdExistsAsPersonID = false;
            for (Person person : personCollection) {
                if (person.getPerson_id() == assignee_id)
                    assigneeIdExistsAsPersonID = true;
            }

            if (assigneeIdExistsAsPersonID) {
                Todo todo = new Todo(title, description, deadline, assignee_id);
                Todo savedTodo = todoItems.create(todo);
                System.out.println("Saved todo: " + savedTodo);
                System.out.println("Operation is Done!");
            }

        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void printAllTodos() {
        try {
            TodoItems todoItems = new TodoItemsImpl(MySQLConnection.getConnection());
            Collection<Todo> todoArrayList = todoItems.findAll();
            for (Todo todo : todoArrayList)
                System.out.println(todo);

        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void printTodoById() {
        try (Scanner scanner = new Scanner(System.in)) {

            TodoItems todoItems = new TodoItemsImpl(MySQLConnection.getConnection());
            System.out.println("Enter ID#: ");
            int id = scanner.nextInt();
            System.out.println(todoItems.findById(id));
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        } catch (InputMismatchException e) {
            System.out.println("Must enter a valid number");
        }
    }

    public static void printByDoneStatus() {
        try (Scanner scanner = new Scanner(System.in)) {
            TodoItems todoItems = new TodoItemsImpl(MySQLConnection.getConnection());
            System.out.println("Enter true/false: ");
            boolean done = scanner.nextBoolean();
            for (Todo todo : todoItems.findByDoneStatus(done)) {
                System.out.println(todo);
            }
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        } catch (InputMismatchException e) {
            System.out.println("Must enter a valid number");
        }

    }
    public static void printByAssigneeId() {
        try (Scanner scanner = new Scanner(System.in)) {
            TodoItems todoItems = new TodoItemsImpl(MySQLConnection.getConnection());
            System.out.println("Enter id#: ");
            int assigneeId = scanner.nextInt();
            for (Todo todo : todoItems.findByAssignee(assigneeId)) {
                System.out.println(todo);
            }
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        } catch (InputMismatchException e) {
            System.out.println("Must enter a valid number");
        }

    }
    public static void printByAssigneeIdWithPerson() {
        try (Scanner scanner = new Scanner(System.in)) {
            TodoItems todoItems = new TodoItemsImpl(MySQLConnection.getConnection());
            System.out.println("Enter id#: ");
            int assigneeId = scanner.nextInt();
            People people = new PeopleImpl(MySQLConnection.getConnection());
            for (Todo todo : todoItems.findByAssignee(people.findById(assigneeId))) {
                System.out.println(todo);
            }
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        } catch (InputMismatchException e) {
            System.out.println("Must enter a valid number");
        }
    }
    public static void printFindByUnassignedTodoItems() {
        try {
            TodoItems todoItems = new TodoItemsImpl(MySQLConnection.getConnection());
            for (Todo todo : todoItems.findByUnassignedTodoItems())
                System.out.println(todo);
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }
    public static void printupdatedTodo() {
        try (Scanner scanner = new Scanner(System.in)) {
            TodoItems todoItems = new TodoItemsImpl(MySQLConnection.getConnection());
            System.out.println("Enter ID#: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Todo formerTodo = todoItems.findById(id);
            if (formerTodo != null) {
                System.out.println("Enter new title: ");
                String newTitle = scanner.nextLine();
                System.out.println("Enter new description: ");
                String newDescritpion = scanner.nextLine();
                System.out.println("Enter new deadline in format \"YYYY-MM-DD\": ");
                String newDeadlineAsString = scanner.nextLine();
                LocalDate newDeadline = LocalDate.parse(newDeadlineAsString);
                System.out.println("Enter new Status done (true/false): ");
                boolean newDone = scanner.nextBoolean(); scanner.nextLine();
                System.out.println("Enter new Assignee Id: ");
                Integer newAssignee_id = null;
                try {
                    newAssignee_id = scanner.nextInt();
                }catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                }finally {
                    scanner.nextLine();
                }

                Todo newTodo = new Todo(formerTodo.getTodo_id(), newTitle, newDescritpion, newDeadline, newDone, newAssignee_id);
                System.out.println(todoItems.update(newTodo));
            }else System.out.println("No such ID");

        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }
}




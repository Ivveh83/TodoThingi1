package collections;

import model.Person;
import sequenzers.PersonIdSequencer;
import java.util.ArrayList;
import java.util.Iterator;

public class PersonDAOCollection {

    ArrayList<Person> personArrayList = new ArrayList<>(); // stored one person that its id is 4

    public Person persist(Person person) { // person data from the method param has no id
        person.setId(PersonIdSequencer.nextId()); // 123456
        personArrayList.add(person); // person data has id
        return person;
    }

    public Person findById(int id) {
        for (Person person : personArrayList){
            if (person.getId() == id) return person;
        }
        System.out.println("No such id does exist");
        return null;
    }

    public Person findByEmail(String email) {
        for (Person person : personArrayList){
            if (person.getEmail() == email) return person;
        }
        System.out.println("No such email does exist");
        return null;
    }

    public ArrayList<Person> findAll() {
        return personArrayList;
    }

    public void remove(int id) {
        boolean objectRemoved = false;
        Iterator itr = personArrayList.iterator();
        while (itr.hasNext()){
            Person person = (Person) itr.next();
            if (person.getId() == id){
                itr.remove();
                System.out.println("Removed object sucessfully");
                objectRemoved = true;
            }
        }
        if (!objectRemoved) System.out.println("Object not found");
    }
}

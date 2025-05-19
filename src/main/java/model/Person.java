package model;

import java.util.Objects;

public class Person {

    private int personId;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Person(int personId, String firstName, String lastName) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPersonId() {
        return personId;
    }
    public void setPersonId(int personId) {this.personId = personId;}
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        if (firstName == null){
            throw new IllegalArgumentException("First name is not allowed to be null");
        }else {
            this.firstName = firstName;
        }
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        if (lastName == null){
            throw new IllegalArgumentException("Last name is not allowed to be null");
        }else {
            this.lastName = lastName;
        }
    }


    @Override
    public String toString(){

//StringBuilder sb = new StringBuilder(); .append(), String result = sb.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(this.getPersonId()).append(", name: ")
                .append(this.getFirstName() + " " + this.getLastName());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName);
    }
}

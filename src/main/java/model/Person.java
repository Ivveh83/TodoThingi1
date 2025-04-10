package model;

import java.util.Objects;

public class Person {

//    static int counter;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;

    public Person(String firstName, String lastName, String email) {
//        this.id = ++counter;
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    public Person(String firstName, String lastName){
        this(firstName, lastName, "mail@example.com");
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (email == null){
            throw new IllegalArgumentException("Email is not allowed to be null");
        }else {
            this.email = email;
        }
    }
    public void setCredentials(AppUser credentials) {
        this.credentials = credentials;
    }
    public AppUser getCredentials() {
        return credentials;
    }

    @Override
    public String toString(){

//StringBuilder sb = new StringBuilder(); .append(), String result = sb.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(this.getId()).append(", name: ")
                .append(this.getFirstName() + " " + this.getLastName())
                .append(", email: ").append(this.getEmail());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName)
                && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }
}

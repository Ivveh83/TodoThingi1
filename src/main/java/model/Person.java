package model;

import java.util.Objects;

public class Person {

    private int personId;
    private String firstName;
    private String lastName;
    private String email;

    //To register data in DataBase
    public Person(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    // To retrieve data from Database
    public Person(int personId, String firstName, String lastName, String email) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (!email.isBlank()){
        }else throw new IllegalArgumentException("Email must not be blank.");
        if (email.contains("@")){
        }else throw new IllegalArgumentException("Email must contain an @-sign.");
        if (!email.substring(email.indexOf("@") + 1).isBlank() &&
                !email.substring(email.indexOf("@") + 1).equals(".")){
        }else throw new IllegalArgumentException("Email must contain a service provider name.");
        if (email.contains(".")){
        }else throw new IllegalArgumentException("Email must contain a \".\"-sign.");
        if (!email.substring(email.indexOf(".") + 1).isBlank()){
        }else throw new IllegalArgumentException("Email must contain a top-level domain.");
        System.out.println("Email is valid");
        this.email = email;
    }


    @Override
    public String toString(){

//StringBuilder sb = new StringBuilder(); .append(), String result = sb.toString();
        StringBuilder sb = new StringBuilder();
        if (this.personId == 0) return null;
        sb.append("id: ").append(this.getPersonId()).append(", name: ")
                .append(this.getFirstName() + " " + this.getLastName()).append(", email: ").append(this.getEmail());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, email);
    }
}

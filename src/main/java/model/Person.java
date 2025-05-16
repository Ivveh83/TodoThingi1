package model;

import java.util.Objects;

public class Person {

//    static int counter;
    private int person_id;
    private String first_name;
    private String last_name;
    private AppUser credentials;

    public Person(String first_name, String last_name) {
//        this.id = ++counter;
        setFirst_name(first_name);
        setLast_name(last_name);
    }

    public Person(int person_id, String first_name, String last_name) {
        this.person_id = person_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getPerson_id() {
        return person_id;
    }
    public void setPerson_id(int person_id) {this.person_id = person_id;}
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        if (first_name == null){
            throw new IllegalArgumentException("First name is not allowed to be null");
        }else {
            this.first_name = first_name;
        }
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        if (last_name == null){
            throw new IllegalArgumentException("Last name is not allowed to be null");
        }else {
            this.last_name = last_name;
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
        sb.append("id: ").append(this.getPerson_id()).append(", name: ")
                .append(this.getFirst_name() + " " + this.getLast_name());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return person_id == person.person_id && Objects.equals(first_name, person.first_name)
                && Objects.equals(last_name, person.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person_id, first_name, last_name);
    }
}

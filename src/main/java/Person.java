public class Person {

    static int counter;
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    Person(String firstName, String lastName, String email) {
        this.id = ++counter;
        setFirstName(firstName);
        if (lastName == null) {
            throw new IllegalArgumentException("Must not be null");
        }
        this.lastName = lastName;
        if (email == null) {
            throw new IllegalArgumentException("Must not be null");
        }
            this.email = email;
    }

    Person(String firstName, String lastName){
        this(firstName, lastName, "mail@example.com");
    }

    public int getId() {
        return id;
    }
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
    public String summary(){

//StringBuilder sb = new StringBuilder(); .append(), String result = sb.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(this.getId()).append(", name: ")
                .append(this.getFirstName() + " " + this.getLastName())
                .append(", email: ").append(this.getEmail());
        return sb.toString();
    }
}
//System.out.println("id: " + this.getId() +
//                ", name: " + this.getFirstName() + " " + this.getLastName() +
//                ", email: " + this.getEmail());
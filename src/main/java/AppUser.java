import java.util.Objects;

public class AppUser {

    private String username;
    private String password;
    private AppRole role;

    AppUser(String username, String password, AppRole role){
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (!username.isEmpty() && username != null && !username.isBlank()) this.username = username;
        else throw new IllegalArgumentException("Not allowed to be empty or null");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!password.isEmpty() && password != null && !password.isBlank()) this.password = password;
        else throw new IllegalArgumentException("Not allowed to be empty or null");
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        if (role != null) this.role = role;
        else throw new IllegalArgumentException("Not allowed to be null");
    }
    @Override
    public String toString(){
        return "Username: " + getUsername() + ", Role: " + getRole();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username) && role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }
}

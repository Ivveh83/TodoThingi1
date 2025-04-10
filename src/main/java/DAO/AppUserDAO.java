package DAO;

import java.util.ArrayList;
import model.AppUser;


public interface AppUserDAO {

    AppUser persist(AppUser appUser);
    AppUser findByUsername(String username);
    ArrayList<AppUser> findAll();
    void remove(String username);
}

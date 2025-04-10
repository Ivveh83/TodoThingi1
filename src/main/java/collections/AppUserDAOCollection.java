package collections;

import DAO.AppUserDAO;
import model.AppUser;
import model.Person;
import sequenzers.AppUserSequencer;

import java.util.ArrayList;
import java.util.Iterator;

public class AppUserDAOCollection implements AppUserDAO {

    ArrayList<AppUser> appUsers;

    public AppUser persist(AppUser appUser) {
        appUser.setId(AppUserSequencer.nextId());
        appUsers.add(appUser);
        return appUser;
    }

    public AppUser findByUsername(String username) {
        return null;
    }

    public ArrayList<AppUser> findAll() {
        return null;
    }

    public void remove(String username) {
        boolean objectRemoved = false;
        Iterator itr = appUsers.iterator();
        while (itr.hasNext()){
            AppUser appUser = (AppUser) itr.next();
            if (appUser.getUsername().equals(username)){
                itr.remove();
                System.out.println("Removed object sucessfully");
                objectRemoved = true;
            }
        }
        if (!objectRemoved) System.out.println("Object not found");
    }

}

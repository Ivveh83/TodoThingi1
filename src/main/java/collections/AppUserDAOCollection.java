package collections;

import DAO.AppUserDAO;
import model.AppUser;
import sequenzers.AppUserSequencer;

import java.util.ArrayList;
import java.util.Iterator;

public class AppUserDAOCollection implements AppUserDAO {

    private static final AppUserDAOCollection INSTANCE = new AppUserDAOCollection();
    ArrayList<AppUser> appUsers = new ArrayList<>();

    private AppUserDAOCollection(){

    }

    public static AppUserDAOCollection getInstance() {
        return INSTANCE;
    }

    public AppUser persist(AppUser appUser) {
        appUser.setId(AppUserSequencer.nextId());
        appUsers.add(appUser);
        return appUser;
    }

    public AppUser findByUsername(String username) {
        for (AppUser appUser : appUsers){
            if (appUser.getUsername().equals(username)) return appUser;
        }
        System.out.println("No such id does exist");
        return null;
    }

    public ArrayList<AppUser> findAll() {
        return appUsers;
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

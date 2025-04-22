package collections;

import DAO.AppUserDAO;
import model.AppRole;
import model.AppUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sequenzers.AppUserSequencer;

import java.util.ArrayList;

class AppUserDAOCollectionTest {

//    AppUser creator;
    AppUser creator = new AppUser("Kalle Anka", "qwerty", AppRole.ROLE_APP_USER);
    AppUserDAO appUserDAO;

    @BeforeEach
    void setUp() {

        appUserDAO = AppUserDAOCollection.getInstance();
        appUserDAO.persist(creator);
    }

    @Test
    void getInstance() {

    }

    @Test
    void persist() {

        AppUser registeredAppUser = appUserDAO.persist(creator);

        Assertions.assertEquals(creator, registeredAppUser);

    }

    @Test
    void findByUsername() {

        AppUser appUser = appUserDAO.findByUsername(creator.getUsername());

        Assertions.assertEquals(creator.getUsername(), appUser.getUsername());
    }

    @Test
    void findAll() {

        ArrayList<AppUser> appUsers1 = new ArrayList<>();
        appUsers1.add(creator);

        ArrayList<AppUser> appUsers = appUserDAO.findAll();

        Assertions.assertEquals(appUsers, appUsers1);
    }

    @Test
    void remove() {

        ArrayList<AppUser> appUsers1 = new ArrayList<>();
        ArrayList<AppUser> appUsers2;

        appUserDAO.remove("Kalle Anka");
        appUsers2 = appUserDAO.findAll();

        Assertions.assertEquals(appUsers1.size(), appUsers2.size());

    }
}
package cz.tul;

import cz.tul.data.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CityDaoTests {

    @Autowired
    private StateDao stateDao;

    @Autowired
    private CityDao cityDao;


    @Test
    public void Test1_createOffer() {

        //usersDao.deleteUsers();

        State state = new State("Albania");

        assertTrue("User creation should return true", stateDao.create(state));

        City city = new City(1,"London",state);

        assertTrue("Offer creation should return true", cityDao.create(city));

    }

    @Test
    public void Test2_listOffers() {

        List<City> cities = cityDao.getCities();
        // Get the offer with ID filled in.
        City city = cities.get(0);

        assertEquals("Should be one offer in database.", 1, cities.size());

        assertEquals("Retrieved offer should match created offer.", city,
                cities.get(0));
    }
    /**
    @Test
    public void Test3_updateOffer() {

        List<Offer> offers = offersDao.getOffers();

        // Get the offer with ID filled in.
        Offer offer = offers.get(0);

        offer.setText("Updated offer text.");
        assertTrue("Offer update should return true", offersDao.update(offer));

        Offer updated = offersDao.getOffer(offer.getId());

        assertEquals("Updated offer should match retrieved updated offer", offer, updated);
    }

    @Test
    public void Test4_getOfferById() {

        List<User> users = usersDao.getAllUsers();

        // Test get by ID ///////
        Offer offer2 = new Offer(users.get(0), "This is a test offer.");

        assertTrue("Offer creation should return true", offersDao.create(offer2));

        List<Offer> userOffers = offersDao.getOffers();
        assertEquals("Should be two offers for user.", 2, userOffers.size());

        List<Offer> secondList = offersDao.getOffers();
        System.out.println(secondList);

        for (Offer current : secondList) {
            Offer retrieved = offersDao.getOffer(current.getId());

            assertEquals("Offer by ID should match offer from list.", current, retrieved);
        }
    }

    @Test
    public void Test5_deleteOffer() {

        List<Offer> offers = offersDao.getOffers();

        // Get the offer with ID filled in.
        Offer offer = offers.get(0);

        // Test deletion
        offersDao.delete(offer.getId());

        List<Offer> finalList = offersDao.getOffers();

        assertEquals("Offers lists should contain one offer.", 1, finalList.size());
    }
    **/
}

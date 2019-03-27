package cz.tul;

import cz.tul.data.State;
import cz.tul.data.StateDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
public class StateDaoTests {

    @Autowired
    private StateDao stateDao;

    private State state1 = new State("Amerika");
    private State state2 = new State("Česká republika");
    private State state3 = new State("Anglie");
    private State state4 = new State("Slovensko");

    @Before
    public void init() {
        stateDao.deleteStates();
    }

    @Test
    public void testCreateRetrieve() {
        stateDao.create(state1);

        List<State> states1 = stateDao.getAllStates();

        System.out.println(states1);

        assertEquals("One state should have been created and retrieved", 1, states1.size());

        assertEquals("Inserted state should match retrieved", state1, states1.get(0));

        stateDao.create(state2);
        stateDao.create(state3);
        stateDao.create(state4);

        List<State> states2 = stateDao.getAllStates();

        assertEquals("Should be four retrieved states.", 4, states2.size());
    }

    @Test
    public void testExists() {
        stateDao.create(state1);
        stateDao.create(state2);
        stateDao.create(state3);

        assertTrue("State should exist.", stateDao.exists(state1.getStateName()));
        assertFalse("State should not exist.", stateDao.exists("Slovinsko"));
    }


}

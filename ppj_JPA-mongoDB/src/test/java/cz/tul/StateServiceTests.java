package cz.tul;

import cz.tul.model.State;
import cz.tul.service.StateService;
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
public class StateServiceTests {

    @Autowired
    private StateService stateService;

    private State state1 = new State("Amerika");
    private State state2 = new State("Česká republika");
    private State state3 = new State("Anglie");
    private State state4 = new State("Slovensko");

    @Before
    public void init() {
        stateService.deleteStates();
    }

    @Test
    public void testCreateRetrieve() {
        stateService.create(state1);

        List<State> states1 = stateService.getAllStates();

        System.out.println(states1);

        assertEquals("One state should have been created and retrieved", 1, states1.size());

        assertEquals("Inserted state should match retrieved", state1, states1.get(0));

        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);

        List<State> states2 = stateService.getAllStates();

        assertEquals("Should be four retrieved states.", 4, states2.size());
    }

    @Test
    public void testExists() {
        stateService.create(state1);
        stateService.create(state2);
        stateService.create(state3);

        assertTrue("State should exist.", stateService.exists(state1.getStateName()));
        assertFalse("State should not exist.", stateService.exists("Slovinsko"));
    }


}

import app.assignments.configuration.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles({"TEST"})
public class AssertTest {
    @Autowired
    ApplicationContext ctx;

    @Test
    public void testContextLoads() throws Exception {
        assertNotNull(this.ctx);
        assertTrue(this.ctx.containsBean("pingMessage"));
    }
}

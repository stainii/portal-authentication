package be.stijnhooft.portal.authentication.repositories;

import be.stijnhooft.portal.authentication.PortalAuthentication;
import be.stijnhooft.portal.authentication.model.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortalAuthentication.class)
@ActiveProfiles("local")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DatabaseSetup("/datasets/UserRepositoryTest-findByUsername-initial.xml")
    @DatabaseTearDown("/datasets/clear.xml")
    public void findByUsernameWhenFound() {
        Optional<User> user = userRepository.findByUsername("stijn");
        assertTrue(user.isPresent());
        assertEquals("stijn", user.get().getUsername());
    }

    @Test
    @DatabaseSetup("/datasets/UserRepositoryTest-findByUsername-initial.xml")
    @DatabaseTearDown("/datasets/clear.xml")
    public void findByUsernameWhenNotFound() {
        Optional<User> user = userRepository.findByUsername("chantal");
        assertFalse(user.isPresent());
    }

}

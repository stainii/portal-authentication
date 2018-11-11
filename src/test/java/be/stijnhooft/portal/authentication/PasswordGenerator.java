package be.stijnhooft.portal.authentication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * With this test, you can create a password, to be used in the actual application.
 */
@RunWith(SpringRunner.class)
public class PasswordGenerator {

    @Test
    public void generate() {
        System.out.println(new BCryptPasswordEncoder().encode("stijn"));
    }

}

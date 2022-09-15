package be.stijnhooft.portal.authentication;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * With this test, you can create a password, to be used in the actual application.
 */
public class PasswordGenerator {

    @Test
    public void generate() {
        System.out.println(new BCryptPasswordEncoder().encode("stijn"));
    }

}

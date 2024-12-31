package be.stijnhooft.portal.authentication.services;

import be.stijnhooft.portal.authentication.model.User;
import be.stijnhooft.portal.authentication.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void loadUserByUsernameWhenFound() {
        User user = new User(1L, "stijn", "passw", "ADMIN");

        doReturn(Optional.of(user)).when(userRepository).findByUsername("stijn");

        UserDetails userDetails = userDetailsService.loadUserByUsername("stijn");
        assertEquals("stijn", userDetails.getUsername());
        assertEquals("passw", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertEquals("ROLE_ADMIN", userDetails.getAuthorities().iterator().next().getAuthority());

        verify(userRepository).findByUsername("stijn");
    }

    @Test
    public void loadUserByUsernameWhenNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> {
            doReturn(Optional.empty()).when(userRepository).findByUsername("test");
            userDetailsService.loadUserByUsername("test");
        });
    }
}

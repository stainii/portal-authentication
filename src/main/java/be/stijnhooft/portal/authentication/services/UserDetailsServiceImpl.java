package be.stijnhooft.portal.authentication.services;

import be.stijnhooft.portal.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .map(user -> {
                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());

                // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
                // And used by auth manager to verify and check user authentication.
                return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
            }).orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

}

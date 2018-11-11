package be.stijnhooft.portal.authentication.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user_auth")
public class User extends UserCredentials {

    @Id
    private Long id;

    private String role;

    public User() {}

    public User(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

package be.stijnhooft.portal.authentication.model;

import lombok.Data;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class UserCredentials {

    protected String username;
    protected String password;

}

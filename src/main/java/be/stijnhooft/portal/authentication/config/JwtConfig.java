package be.stijnhooft.portal.authentication.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {

    @Value("${security.jwt.uri:/auth/**}")
    @Getter
    private String uri;

    @Value("${security.jwt.header:Authorization}")
    @Getter
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    @Getter
    private String prefix;

    @Value(value = "${security.jwt.expiration:#{24*60*60}}")
    @Getter
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    @Getter
    private String secret;

}

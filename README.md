# portal-authentication
[![Build Status](http://portal-ci.westeurope.cloudapp.azure.com/buildStatus/icon?job=portal-authentication/master)](http://portal-ci.westeurope.cloudapp.azure.com/job/portal-authentication/job/master/)

Authentication server for the portal.

## How does it work?
For more information on how and why it's built, check out [omarelgabrys-blog](https://medium.com/omarelgabrys-blog/microservices-with-spring-boot-authentication-with-jwt-part-3-fafc9d7187e8).

## Security secrets
In order to run this application safely, it's important to override the following spring properties with your own value:
* `security.jwt.expiration`
    * how long should a token be valid, in minutes
* `security.jwt.secret`
    * a random token that's used to hash the JWT token
    * use the same secret for every module of this portal.

## How can I add my own user in the database?
At this moment, there is no registration functionality written yet.
You need to add your own user manually in the database.

To generate a password hash, you can use `be.stijnhooft.portal.authentication.PasswordGenerator` in the `src/test/java` folder. 

## Environment variables
| Name | Example value | Description | Required? |
| ---- | ------------- | ----------- | -------- |
| POSTGRES_PASSWORD | secret | Password to log in to the database | required
| JAVA_OPTS_PORTAL_AUTHENTICATION | -Xmx400m -Xms400m | Java opts you want to pass to the JVM | optional

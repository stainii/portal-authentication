# portal-authentication
[![Build Status](https://server.stijnhooft.be/jenkins/buildStatus/icon?job=portal-authentication/master)](https://server.stijnhooft.be/jenkins/job/portal-authentication/master)

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
| JAVA_OPTS_AUTHENTICATION | -Xmx400m -Xms400m | Java opts you want to pass to the JVM | optional

### Release
#### How to release
To release a module, this project makes use of the JGitflow plugin and the Dockerfile-maven-plugin.

1. Make sure all changes have been committed and pushed to Github.
1. Switch to the dev branch.
1. Make sure that the dev branch has at least all commits that were made to the master branch
1. Make sure that your Maven has been set up correctly (see below)
1. Run `mvn jgitflow:release-start -Pproduction`.
1. Run `mvn jgitflow:release-finish -Pproduction`.
1. In Github, mark the release as latest release.
1. Congratulations, you have released both a Maven and a Docker build!

More information about the JGitflow plugin can be found [here](https://gist.github.com/lemiorhan/97b4f827c08aed58a9d8).

##### Maven configuration
At the moment, releases are made on a local machine. No Jenkins job has been made (yet).
Therefore, make sure you have the following config in your Maven `settings.xml`;

````$xml
<servers>
		<server>
			<id>docker.io</id>
			<username>your_username</username>
			<password>*************</password>
		</server>
		<server>
			<id>portal-nexus-releases</id>
			<username>your_username</username>
            <password>*************</password>
		</server>
	</servers>
````
* docker.io points to the Docker Hub.
* portal-nexus-releases points to my personal Nexus (see `<distributionManagement>` in the project's `pom.xml`)

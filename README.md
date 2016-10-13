# guice-classpath-scanning
An attempt at getting classpath scanning in Guice

## Finding outdated dependencies
    ./gradlew dependencyUpdates

## Using
### Maven
    <dependency>
      <groupId>com.github.RichoDemus.guice-classpath-scanning</groupId>
      <artifactId>guice-classpath-scanner</artifactId>
      <version>prerelease</version>
    </dependency>
    
    <repositories>
      <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
      </repository>
    </repositories>
    
### Code
    Guice.createInjector(new ClassPathScanningModule("com.yourpackage"));
    
## Development

### Building
    ./gradlew
    
### Signing and uploading
You'll need a gradle.properties with the following contents
```
signing.keyId=24875D73
signing.password=secret
signing.secretKeyRingFile=/Users/me/.gnupg/secring.gpg

ossrhUsername=your-jira-id
ossrhPassword=your-jira-password
```

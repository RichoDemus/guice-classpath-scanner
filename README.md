# guice-classpath-scanning
An attempt at getting classpath scanning in Guice

## Using
### Maven
```
<groupId>com.richodemus.scanner</groupId>
<artifactId>guice-classpath-scanner</artifactId>
<version>1.0.0</version>
```

### Gradle
```
compile 'com.richodemus.scanner:guice-classpath-scanner:1.0.0'
```
    
### Code
    Guice.createInjector(new ClassPathScanningModule("com.yourpackage"));
    
## Development

### Building
    ./gradlew
    
## Finding outdated dependencies
    ./gradlew dependencyUpdates
    
### Signing and uploading
You'll need a gradle.properties with the following contents
```
signing.keyId=24875D73
signing.password=secret
signing.secretKeyRingFile=/Users/me/.gnupg/secring.gpg

ossrhUsername=your-jira-id
ossrhPassword=your-jira-password
```
and then run

    ./gradlew uploadArchives

# guice-classpath-scanning
An attempt at getting classpath scanning in Guice

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

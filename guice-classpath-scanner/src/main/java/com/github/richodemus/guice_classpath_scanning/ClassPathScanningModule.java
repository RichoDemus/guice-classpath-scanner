package com.github.richodemus.guice_classpath_scanning;

import com.google.inject.AbstractModule;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassPathScanningModule extends AbstractModule {
    final Logger logger = LoggerFactory.getLogger(getClass());
    private final String classPath;

    public ClassPathScanningModule(final String classPath) {

        this.classPath = classPath;
    }

    @Override
    protected void configure() {
        Reflections reflections = new Reflections(classPath, new SubTypesScanner(false));
        reflections.getAllTypes().forEach(System.out::println);

        reflections.getAllTypes().stream().map(this::stringToClass);
    }

    private Class stringToClass(String className) {
        try {
            return this.getClass().getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            logger.error("Got ClassNotFoundException when loading class [{}] ", className, e);
            throw new IllegalStateException(e);
        }
    }
}

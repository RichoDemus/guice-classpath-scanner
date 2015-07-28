package com.github.richodemus.guice_classpath_scanning;

import com.google.inject.AbstractModule;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class ClassPathScanningModule extends AbstractModule {
    final Logger logger = LoggerFactory.getLogger(getClass());
    final private Reflections reflections;

    public ClassPathScanningModule(final String classPath) {
        reflections = new Reflections(classPath, new SubTypesScanner(false));
    }

    @Override
    protected void configure() {
        reflections.getAllTypes().stream()
                .map(this::stringToClass)
                .filter(this::onlyInterfacesAndAbstractClasses)
                .map(this::findImplementationForInterface)
                .filter(obj -> obj != null)
                .forEach(pair -> bind(pair.getTheInterface()).to(pair.getTheClass()));
    }

    private boolean onlyInterfacesAndAbstractClasses(Class aClass) {
        return aClass.isInterface() || Modifier.isAbstract(aClass.getModifiers());
    }

    private Class stringToClass(String className) {
        try {
            return this.getClass().getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            logger.error("Got ClassNotFoundException when loading class [{}] ", className, e);
            throw new IllegalStateException(e);
        }
    }

    private InterfaceAndImplementation findImplementationForInterface(Class theInterface) {
        final Set<Class> implementations = reflections.getSubTypesOf(theInterface);

        final List<Class> classes = new ArrayList<>();
        classes.addAll(implementations);

        //Todo: should call bind multiple times instead, to let guice handle this scenario and then rename the test
        if (classes.size() > 1) {
            final StringJoiner joiner = new StringJoiner(",", "{", "}");
            classes.stream().map(Class::getName).forEach(joiner::add);

            logger.error("Found more than 1 implementation for [{}]: {}", theInterface.getClass().getName(), joiner.toString());
            throw new IllegalStateException("Found more than 1 implementation for " + theInterface.getClass().getName() + ": " + joiner.toString());
        }

        if (classes.size() == 0) {
            //TODO this should be made nicer
            return null;
        }

        return new InterfaceAndImplementation(theInterface, classes.get(0));
    }

}

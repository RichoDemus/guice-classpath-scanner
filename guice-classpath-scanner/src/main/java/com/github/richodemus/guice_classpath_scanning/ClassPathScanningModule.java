package com.github.richodemus.guice_classpath_scanning;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;

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
                .forEach(this::bindInterfaceToImplementation);
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

    private InterfaceAndImplementations findImplementationForInterface(Class theInterface) {
        return new InterfaceAndImplementations(theInterface, reflections.getSubTypesOf(theInterface));
    }

    /**
     * If there are more more than 1 implementation Guice will complain, this is intentional because we want an interface to have only 1 implementation
     *
     * @param holder
     */
    private void bindInterfaceToImplementation(InterfaceAndImplementations holder) {
        //todo refactor this, maybe have two different streams in configure, one for named and one for unnamed..
        holder.getTheImplementations().forEach(impl -> {
            final Annotation annotation = impl.getAnnotation(Named.class);
            if (annotation == null) {
                logger.debug("binding {} to {}", holder.getTheInterface().getSimpleName(), impl.getSimpleName());
                bind(holder.getTheInterface()).to(impl);
                return;
            }
            final Named namedAnnotation = (Named) annotation;
            final String name = namedAnnotation.value();
            logger.debug("binding {} named {} to {}", holder.getTheInterface().getSimpleName(), name, impl.getSimpleName());
            bind(holder.getTheInterface()).annotatedWith(Names.named(name)).to(impl);
        });
    }
}

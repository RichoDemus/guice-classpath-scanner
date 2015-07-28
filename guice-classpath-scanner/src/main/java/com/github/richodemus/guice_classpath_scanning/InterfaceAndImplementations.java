package com.github.richodemus.guice_classpath_scanning;

import java.util.Set;

class InterfaceAndImplementations {
    private final Class theInterface;
    private final Set<Class> itsImplementations;

    InterfaceAndImplementations(Class theInterface, Set<Class> itsImplementations) {
        this.theInterface = theInterface;
        this.itsImplementations = itsImplementations;
    }

    Class getTheInterface() {
        return theInterface;
    }

    Set<Class> getTheImplementations() {
        return itsImplementations;
    }
}

package com.github.richodemus.guice_classpath_scanning;

class InterfaceAndImplementation {
    private final Class theInterface;
    private final Class itsClass;

    InterfaceAndImplementation(Class theInterface, Class itsClass) {
        this.theInterface = theInterface;
        this.itsClass = itsClass;
    }

    Class getTheInterface() {
        return theInterface;
    }

    Class getTheClass() {
        return itsClass;
    }
}

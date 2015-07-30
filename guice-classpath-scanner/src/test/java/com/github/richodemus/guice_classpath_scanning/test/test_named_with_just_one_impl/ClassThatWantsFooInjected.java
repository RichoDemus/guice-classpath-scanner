package com.github.richodemus.guice_classpath_scanning.test.test_named_with_just_one_impl;

import javax.inject.Inject;
import javax.inject.Named;

public class ClassThatWantsFooInjected {
    private final InterfaceWithNamedImplementation injected;

    @Inject
    public ClassThatWantsFooInjected(@Named("foo") InterfaceWithNamedImplementation injected) {
        this.injected = injected;
    }

    public InterfaceWithNamedImplementation getInjected() {
        return injected;
    }
}

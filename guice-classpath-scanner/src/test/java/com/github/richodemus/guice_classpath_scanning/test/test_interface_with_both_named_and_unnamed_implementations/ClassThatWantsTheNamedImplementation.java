package com.github.richodemus.guice_classpath_scanning.test.test_interface_with_both_named_and_unnamed_implementations;

import javax.inject.Inject;
import javax.inject.Named;

public class ClassThatWantsTheNamedImplementation {
    private final InterfaceWithBothNamedAndUnnamedImplementations injected;

    @Inject
    public ClassThatWantsTheNamedImplementation(@Named("foo") InterfaceWithBothNamedAndUnnamedImplementations injected) {
        this.injected = injected;
    }

    public InterfaceWithBothNamedAndUnnamedImplementations getInjected() {
        return injected;
    }
}

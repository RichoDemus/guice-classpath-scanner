package com.github.richodemus.guice_classpath_scanning.test.test_interface_with_both_named_and_unnamed_implementations;

import javax.inject.Inject;

public class ClassThatWantsTheUnnamedImplementation {
    private final InterfaceWithBothNamedAndUnnamedImplementations injected;

    @Inject
    public ClassThatWantsTheUnnamedImplementation(InterfaceWithBothNamedAndUnnamedImplementations injected) {
        this.injected = injected;
    }

    public InterfaceWithBothNamedAndUnnamedImplementations getInjected() {
        return injected;
    }
}

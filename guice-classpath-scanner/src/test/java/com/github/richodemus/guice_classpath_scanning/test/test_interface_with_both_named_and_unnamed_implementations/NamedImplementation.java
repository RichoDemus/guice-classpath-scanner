package com.github.richodemus.guice_classpath_scanning.test.test_interface_with_both_named_and_unnamed_implementations;

import javax.inject.Named;

@Named("foo")
public class NamedImplementation implements InterfaceWithBothNamedAndUnnamedImplementations {
    @Override
    public String foo() {
        return "bar";
    }
}

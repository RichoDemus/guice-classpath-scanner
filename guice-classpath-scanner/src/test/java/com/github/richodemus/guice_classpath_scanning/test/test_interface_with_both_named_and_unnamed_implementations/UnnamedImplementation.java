package com.github.richodemus.guice_classpath_scanning.test.test_interface_with_both_named_and_unnamed_implementations;

public class UnnamedImplementation implements InterfaceWithBothNamedAndUnnamedImplementations {
    @Override
    public String foo() {
        return "bar";
    }
}

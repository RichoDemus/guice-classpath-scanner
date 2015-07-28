package com.github.richodemus.guice_classpath_scanning.test.interface_with_two_implementations;

public class TestInterfaceWithMultipleImplementationsImpl1 implements TestInterfaceWithMultipleImplementations {
    @Override
    public String foo() {
        return "bar";
    }
}

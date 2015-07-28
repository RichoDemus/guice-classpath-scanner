package com.github.richodemus.guice_classpath_scanning.test.interface_with_impl;

public class TestInterfaceImpl implements TestInterface {
    @Override
    public String foo() {
        return "bar";
    }
}

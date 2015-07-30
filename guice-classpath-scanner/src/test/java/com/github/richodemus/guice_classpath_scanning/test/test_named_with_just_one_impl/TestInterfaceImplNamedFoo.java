package com.github.richodemus.guice_classpath_scanning.test.test_named_with_just_one_impl;

import javax.inject.Named;

@Named("foo")
public class TestInterfaceImplNamedFoo implements InterfaceWithNamedImplementation {
    @Override
    public String foo() {
        return "bar";
    }
}

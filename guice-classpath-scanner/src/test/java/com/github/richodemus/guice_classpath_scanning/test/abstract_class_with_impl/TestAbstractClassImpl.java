package com.github.richodemus.guice_classpath_scanning.test.abstract_class_with_impl;

public class TestAbstractClassImpl  extends TestAbstractClass
{
    @Override
    public String foo() {
        return "bar";
    }
}

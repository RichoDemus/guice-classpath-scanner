package com.github.richodemus.guice_classpath_scanning.test.scan_here;

public class TestInterfaceImpl implements TestInterface {
    @Override
    public String foo() {
        return "bar";
    }
}

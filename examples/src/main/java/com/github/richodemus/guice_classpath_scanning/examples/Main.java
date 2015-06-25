package com.github.richodemus.guice_classpath_scanning.examples;

import com.github.richodemus.guice_classpath_scanning.ClassPathScanningModule;
import com.github.richodemus.guice_classpath_scanning.examples.scan_these.MyInterface;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule("com.github.richodemus.guice_classpath_scanning.examples.scan_these"));

        final MyInterface instance = injector.getInstance(MyInterface.class);

        System.out.println("Foo: " + instance.bar());
    }
}

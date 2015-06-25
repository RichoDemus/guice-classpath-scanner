package com.github.richodemus.guice_classpath_scanning;

import com.google.inject.AbstractModule;

public class ClassPathScanningModule extends AbstractModule {
    private final String classPath;

    public ClassPathScanningModule(final String classPath) {

        this.classPath = classPath;
    }

    @Override
    protected void configure() {

    }
}

package com.github.richodemus.guice_classpath_scanning;

import com.github.richodemus.guice_classpath_scanning.test.scan_here.TestInterface;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.Test;

public class ClassPathScanningModuleTest {
    @Test
    public void shouldFindAndInjectImplementationOnClassPath() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule("com.github.richodemus.guice_classpath_scanning.test.scan_here"));

        final TestInterface instance = injector.getInstance(TestInterface.class);

        Assert.assertNotNull(instance);
        Assert.assertEquals("bar", instance.foo());
    }
}
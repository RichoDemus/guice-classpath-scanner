package com.github.richodemus.guice_classpath_scanning;

import com.github.richodemus.guice_classpath_scanning.test.scan_here.TestAbstractClass;
import com.github.richodemus.guice_classpath_scanning.test.scan_here.TestInterface;
import com.github.richodemus.guice_classpath_scanning.test.scan_here.TestInterfaceWithoutImplementation;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.Test;

public class ClassPathScanningModuleTest {
    @Test
    public void shouldFindAndInjectImplementationOfInterfaceOnClassPath() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule("com.github.richodemus.guice_classpath_scanning.test.scan_here"));

        final TestInterface instance = injector.getInstance(TestInterface.class);

        Assert.assertNotNull(instance);
        Assert.assertEquals("bar", instance.foo());
    }

    @Test
    public void shouldFindAndInjectImplementationOfAbstractClassOnClassPath() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule("com.github.richodemus.guice_classpath_scanning.test.scan_here"));

        final TestAbstractClass instance = injector.getInstance(TestAbstractClass.class);

        Assert.assertNotNull(instance);
        Assert.assertEquals("bar", instance.foo());
    }

    @Test(expected = ConfigurationException.class)
    public void shouldGetOriginalGuiceExceptionIfNoImplementationFound() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule("com.github.richodemus.guice_classpath_scanning.test.scan_here"));

        final TestInterfaceWithoutImplementation instance = injector.getInstance(TestInterfaceWithoutImplementation.class);
    }
}

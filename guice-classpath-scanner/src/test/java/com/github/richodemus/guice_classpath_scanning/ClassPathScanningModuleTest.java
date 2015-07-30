package com.github.richodemus.guice_classpath_scanning;

import com.github.richodemus.guice_classpath_scanning.test.abstract_class_with_impl.TestAbstractClass;
import com.github.richodemus.guice_classpath_scanning.test.interface_with_impl.TestInterface;
import com.github.richodemus.guice_classpath_scanning.test.interface_with_two_implementations.TestInterfaceWithMultipleImplementations;
import com.github.richodemus.guice_classpath_scanning.test.interface_without_impl.TestInterfaceWithoutImplementation;
import com.google.inject.ConfigurationException;
import com.google.inject.CreationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Assert;
import org.junit.Test;

public class ClassPathScanningModuleTest {
    @Test
    public void shouldFindAndInjectImplementationOfInterfaceOnClassPath() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule(TestInterface.class.getPackage().getName()));

        final TestInterface instance = injector.getInstance(TestInterface.class);

        Assert.assertNotNull(instance);
        Assert.assertEquals("bar", instance.foo());
    }

    @Test
    public void shouldFindAndInjectImplementationOfAbstractClassOnClassPath() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule(TestAbstractClass.class.getPackage().getName()));

        final TestAbstractClass instance = injector.getInstance(TestAbstractClass.class);

        Assert.assertNotNull(instance);
        Assert.assertEquals("bar", instance.foo());
    }

    @Test(expected = ConfigurationException.class)
    public void shouldGetOriginalGuiceExceptionIfNoImplementationFound() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule(TestInterfaceWithoutImplementation.class.getPackage().getName()));

        final TestInterfaceWithoutImplementation instance = injector.getInstance(TestInterfaceWithoutImplementation.class);
    }

    @Test(expected = CreationException.class)
    public void shouldGetOriginalGuiceExceptionIfMultipleImplementationsAreFound() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule(TestInterfaceWithMultipleImplementations.class.getPackage().getName()));

        final TestInterfaceWithMultipleImplementations instance = injector.getInstance(TestInterfaceWithMultipleImplementations.class);
    }
}

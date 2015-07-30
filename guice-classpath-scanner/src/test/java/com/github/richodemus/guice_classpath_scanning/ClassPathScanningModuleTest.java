package com.github.richodemus.guice_classpath_scanning;

import com.github.richodemus.guice_classpath_scanning.test.abstract_class_with_impl.TestAbstractClass;
import com.github.richodemus.guice_classpath_scanning.test.abstract_class_with_impl.TestAbstractClassImpl;
import com.github.richodemus.guice_classpath_scanning.test.interface_with_impl.TestInterface;
import com.github.richodemus.guice_classpath_scanning.test.interface_with_impl.TestInterfaceImpl;
import com.github.richodemus.guice_classpath_scanning.test.interface_with_two_implementations.TestInterfaceWithMultipleImplementations;
import com.github.richodemus.guice_classpath_scanning.test.interface_without_impl.TestInterfaceWithoutImplementation;
import com.github.richodemus.guice_classpath_scanning.test.test_interface_with_both_named_and_unnamed_implementations.ClassThatWantsTheNamedImplementation;
import com.github.richodemus.guice_classpath_scanning.test.test_interface_with_both_named_and_unnamed_implementations.ClassThatWantsTheUnnamedImplementation;
import com.github.richodemus.guice_classpath_scanning.test.test_interface_with_both_named_and_unnamed_implementations.InterfaceWithBothNamedAndUnnamedImplementations;
import com.github.richodemus.guice_classpath_scanning.test.test_interface_with_both_named_and_unnamed_implementations.NamedImplementation;
import com.github.richodemus.guice_classpath_scanning.test.test_interface_with_both_named_and_unnamed_implementations.UnnamedImplementation;
import com.github.richodemus.guice_classpath_scanning.test.test_named_with_just_one_impl.ClassThatWantsFooInjected;
import com.github.richodemus.guice_classpath_scanning.test.test_named_with_just_one_impl.InterfaceWithNamedImplementation;
import com.github.richodemus.guice_classpath_scanning.test.test_named_with_just_one_impl.TestInterfaceImplNamedFoo;
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
        Assert.assertEquals(TestInterfaceImpl.class, instance.getClass());
    }

    @Test
    public void shouldFindAndInjectImplementationOfAbstractClassOnClassPath() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule(TestAbstractClass.class.getPackage().getName()));

        final TestAbstractClass instance = injector.getInstance(TestAbstractClass.class);

        Assert.assertNotNull(instance);
        Assert.assertEquals("bar", instance.foo());
        Assert.assertEquals(TestAbstractClassImpl.class, instance.getClass());
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

    @Test
    public void shouldInjectNamedImplementation() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule(InterfaceWithNamedImplementation.class.getPackage().getName()));

        final ClassThatWantsFooInjected target = injector.getInstance(ClassThatWantsFooInjected.class);

        Assert.assertNotNull(target.getInjected());
        Assert.assertEquals("bar", target.getInjected().foo());
        Assert.assertEquals(TestInterfaceImplNamedFoo.class, target.getInjected().getClass());
    }

    @Test
    public void shouldHandleBothNamedAndUnnamedImplementations() throws Exception {
        final Injector injector = Guice.createInjector(new ClassPathScanningModule(InterfaceWithBothNamedAndUnnamedImplementations.class.getPackage().getName()));

        final ClassThatWantsTheNamedImplementation named = injector.getInstance(ClassThatWantsTheNamedImplementation.class);

        Assert.assertNotNull(named.getInjected());
        Assert.assertEquals("bar", named.getInjected().foo());
        Assert.assertEquals("Should have injected the named implementation", NamedImplementation.class, named.getInjected().getClass());


        final ClassThatWantsTheUnnamedImplementation unnamed = injector.getInstance(ClassThatWantsTheUnnamedImplementation.class);

        Assert.assertNotNull(unnamed.getInjected());
        Assert.assertEquals("bar", unnamed.getInjected().foo());
        Assert.assertEquals("Should have injected the unnamed implementation", UnnamedImplementation.class, unnamed.getInjected().getClass());
    }
}

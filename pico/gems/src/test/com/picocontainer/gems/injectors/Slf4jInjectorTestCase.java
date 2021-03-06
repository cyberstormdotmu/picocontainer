package com.picocontainer.gems.injectors;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;

import com.picocontainer.DefaultPicoContainer;
import com.thoughtworks.xstream.XStream;

public class Slf4jInjectorTestCase {

    public static class Foo {
        private final Logger logger;
        public Foo(final Logger logger) {
            this.logger = logger;
        }
    }

    @Test
    public void thatItInjectsTheApplicableInstance() throws NoSuchFieldException, IllegalAccessException {

        DefaultPicoContainer pico = new DefaultPicoContainer();
        pico.addAdapter(new Slf4JInjector());
        pico.addComponent(Foo.class);

        Foo foo = pico.getComponent(Foo.class);

        assertNotNull(foo);
        assertNotNull(foo.logger);

        assertTrue(new XStream().toXML(foo.logger).contains("<name>"+Foo.class.getName()+"</name>"));


    }



}
package com.bitresolution.spring.javafx;

import org.springframework.context.ApplicationContext;

import java.net.URL;

public class SpringFxmlLoaderFactory {

    private static SpringFxmlLoaderFactory INSTANCE = null;
    private final ApplicationContext context;

    /**
     * Creates a new SpringFXMLLoaderFactory instance.
     *
     * @param context Spring application context to use to provide the controllers
     */
    private SpringFxmlLoaderFactory(ApplicationContext context) {
        this.context = context;
    }

    public static SpringFxmlLoaderFactory initialise(ApplicationContext context) {
        synchronized (SpringFxmlLoaderFactory.class) {
            if(INSTANCE == null) {
                if(context == null) {
                    throw new IllegalArgumentException("Context can not be null");
                }
                INSTANCE = new SpringFxmlLoaderFactory(context);
            }
            return INSTANCE;
        }
    }

    public static SpringFxmlLoader getLoader(URL location) {
        synchronized (SpringFxmlLoaderFactory.class) {
            if (INSTANCE == null) {
                throw new RuntimeException("SpringFxmlLoaderFactory not initialised");
            }
            return new SpringFxmlLoader(INSTANCE.context, location);
        }
    }

    public static SpringFxmlLoader getLoader() {
        synchronized (SpringFxmlLoaderFactory.class) {
            if (INSTANCE == null) {
                throw new RuntimeException("SpringFxmlLoaderFactory not initialised");
            }
            return new SpringFxmlLoader(INSTANCE.context);
        }
    }
}

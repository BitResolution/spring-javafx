package com.bitresolution.spring.javafx;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

public class SpringManagedFxBuilder<T> implements SpringFxBuilder<T> {

    private final Class<T> type;
    private final AutowireCapableBeanFactory beanFactory;

    public SpringManagedFxBuilder(Class<T> type, AutowireCapableBeanFactory beanFactory) {
        this.type = type;
        this.beanFactory = beanFactory;
    }

    @Override
    public Class<T> getBuilderType() {
        return type;
    }

    /**
     * Builds and returns the object.
     */
    @Override
    public T build() {
        return beanFactory.createBean(type);
    }
}

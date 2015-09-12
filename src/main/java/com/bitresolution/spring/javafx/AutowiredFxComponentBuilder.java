package com.bitresolution.spring.javafx;

import javafx.util.Builder;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

public class AutowiredFxComponentBuilder<T> implements FxComponentBuilder<T> {

    private final Builder<T> builder;
    private final AutowireCapableBeanFactory beanFactory;

    public AutowiredFxComponentBuilder(Builder<T> builder, AutowireCapableBeanFactory beanFactory) {
        this.builder = builder;
        this.beanFactory = beanFactory;
    }

    /**
     * Builds and returns the object.
     */
    @Override
    public T build() {
        T thing = builder.build();
        beanFactory.autowireBean(thing);
        return thing;
    }

    @Override
    public Class<T> getBuilderType() {
        throw new IllegalStateException("This method should never be called");
    }
}

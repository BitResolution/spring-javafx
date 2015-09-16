package com.bitresolution.spring.javafx;

import javafx.util.Builder;

public class SpringJavaFxBuilderAdaptor<T> implements SpringFxBuilder<T> {

    public static <T> SpringFxBuilder<T> adaptBuilder(Class<T> type, Builder<T> builder) {
        return new SpringJavaFxBuilderAdaptor<>(type, builder);
    }

    private final Class<T> type;
    private final Builder<T> builder;

    public SpringJavaFxBuilderAdaptor(Class<T> type, Builder<T> builder) {
        this.type = type;
        this.builder = builder;
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
        return builder.build();
    }
}

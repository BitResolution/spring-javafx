package com.bitresolution.spring.javafx;

import javafx.util.Builder;

public interface FxComponentBuilder<T> extends Builder<T> {

    Class<T> getBuilderType();

    static <T> FxComponentBuilder<T> adaptBuilder(Class<T> type, Builder<T> builder) {
        return new Adaptor<>(type, builder);
    }

    class Adaptor<T> implements FxComponentBuilder<T> {

        private final Class<T> type;
        private final Builder<T> builder;

        public Adaptor(Class<T> type, Builder<T> builder) {
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
}

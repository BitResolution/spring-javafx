package com.bitresolution.spring.javafx;

import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FxBuilderRegistry implements BuilderFactory {

    public final Map<Class<?>, FxComponentBuilder<?>> builders;
    private final SpringFxBuilderFactory builderFactory;

    @Autowired
    public FxBuilderRegistry(SpringFxBuilderFactory builderFactory) {
        this.builderFactory = builderFactory;
        this.builders = new HashMap<>();
    }

    /**
     * Returns a builder suitable for constructing instances of the given type.
     *
     * @param type
     * @return A builder for the given type, or <tt>null</tt> if this factory does not
     * produce builders for the type.
     */
    @Override
    public Builder<?> getBuilder(Class<?> type) {
        return builders.getOrDefault(type, builderFactory.getBuilder(type));
    }

    public void register(FxComponentBuilder<?> builder) {
        builders.put(builder.getBuilderType(), builder);
    }
}

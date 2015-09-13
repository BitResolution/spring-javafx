package com.bitresolution.spring.javafx;

import javafx.util.Builder;
import javafx.util.BuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SpringFxBuilderFactory implements BuilderFactory {

    public final Map<Class<?>, SpringFxBuilder<?>> builders;
    private final SpringDefaultFxBuilderFactory builderFactory;

    @Autowired
    public SpringFxBuilderFactory(SpringDefaultFxBuilderFactory builderFactory) {
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

    public void register(SpringFxBuilder<?> builder) {
        builders.put(builder.getBuilderType(), builder);
    }
}

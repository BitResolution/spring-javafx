package com.bitresolution.spring.javafx;

import javafx.util.Builder;

/**
 * Extension of the JavaFX @{link Builder} which includes the @{Class} type produced by the builder. This allows
 * for the creation of maps of builders to the class(es) thye apply to.
 * @param <T>
 */
public interface SpringFxBuilder<T> extends Builder<T> {

    /**
     * Accesses the type the builder produces
     * @return the type produced
     */
    Class<T> getBuilderType();

}

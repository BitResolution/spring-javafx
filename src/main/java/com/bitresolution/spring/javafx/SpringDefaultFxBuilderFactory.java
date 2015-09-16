package com.bitresolution.spring.javafx;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import static com.bitresolution.spring.javafx.SpringJavaFxBuilderAdaptor.adaptBuilder;

/**
 * Alternate implementation of @{link JavaFXBuilderFactory} that returns either a SpringFxBuilder or null based on the
 * following algorithm:
 * <pre>
 *     <code>
 *         if(@{link JavaFXBuilderFactory#getBuilder(Class<?>} is null AND the class is not annotated with
 *         @{link FXComponent}
 *              return null
 *         if(@{link JavaFXBuilderFactory#getBuilder(Class<?>} is null AND the class <em>is</em> annotated with
 *         @{link FXComponent}
 *              return a @{link SpringManagedFxBuilder}
*          otherwise
 *              return a @{link Builder} wrapped in a @{link SpringJavaFxBuilderAdaptor}
 *     </code>
 * </pre>
 */
@Component
public class SpringDefaultFxBuilderFactory {

    private final AutowireCapableBeanFactory beanFactory;
    private final JavaFXBuilderFactory javaFXBuilderFactory;

    /**
     * Default constuctor for this class. The @{link AutowireCapableBeanFactory} is use to create instance of JavaFX
     * component subclasses which are capable of constructor injection.
     * @param beanFactory the application context's BeanFactory
     */
    @Autowired
    public SpringDefaultFxBuilderFactory(AutowireCapableBeanFactory beanFactory) {
        this(beanFactory, new JavaFXBuilderFactory());
    }

    /**
     * Internal constructor for use by tests and subclass of this class
     * @param beanFactory the application context's BeanFactory
     * @param javaFXBuilderFactory the default JavaFX @{link JavaFXBuilderFactory}
     */
     protected SpringDefaultFxBuilderFactory(AutowireCapableBeanFactory beanFactory, JavaFXBuilderFactory javaFXBuilderFactory) {
        this.beanFactory = beanFactory;
        this.javaFXBuilderFactory = javaFXBuilderFactory;
    }

    /**
     * Returns a builder suitable for constructing instances of the given type.
     *
     * @param type the Class of object to locate a builder for
     * @param <T> the Type of object to locate a builder for
     * @return
     * A builder for the given type, or <tt>null</tt> if this factory does not
     * produce builders for the type.
     */
    @SuppressWarnings("unchecked")
    public <T> SpringFxBuilder<T> getBuilder(Class<T> type) {
        Builder<T> builder = (Builder<T>) javaFXBuilderFactory.getBuilder(type);
        if(builder == null ) {
            return isFxComponent(type) ? new SpringManagedFxBuilder<>(type, beanFactory) : null;
        }
        else {
            return adaptBuilder(type, builder);
        }
    }

    /**
     * Determines whether a given type has a Spring annotation and therefore is in the applicationContext associated
     * with the @{AutowireCapableBeanFactory} supplied in the constructor
     *
     * @param type the Class of object to locate a builder for
     * @param <T> the Type of object to locate a builder for
     * @return true is the type is annotated with the @{link FXComponent} annotation
     */
    private <T> boolean isFxComponent(Class<T> type) {
        return type.isAnnotationPresent(FXComponent.class);
    }
}

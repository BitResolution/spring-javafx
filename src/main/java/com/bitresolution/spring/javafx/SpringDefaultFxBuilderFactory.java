package com.bitresolution.spring.javafx;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import static com.bitresolution.spring.javafx.SpringFxBuilder.adaptBuilder;

@Component
public class SpringDefaultFxBuilderFactory {

    private final AutowireCapableBeanFactory beanFactory;
    private final JavaFXBuilderFactory javaFXBuilderFactory;

    @Autowired
    public SpringDefaultFxBuilderFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        javaFXBuilderFactory = new JavaFXBuilderFactory();
    }

    @SuppressWarnings("unchecked")
    public <T> SpringFxBuilder<T> getBuilder(Class<T> type) {
        Builder<T> builder = (Builder<T>) javaFXBuilderFactory.getBuilder(type);
        if(builder == null || isFxComponent(type)) {
            return new SpringManagedFxBuilder<>(type, beanFactory);
        }
        else {
            return adaptBuilder(type, builder);
        }
    }

    private <T> boolean isFxComponent(Class<T> type) {
        return type.isAnnotationPresent(FXComponent.class);
    }
}

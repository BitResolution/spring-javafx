package com.bitresolution.spring.javafx;

import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import static com.bitresolution.spring.javafx.FxComponentBuilder.adaptBuilder;

@Component
public class SpringFxBuilderFactory {

    private final AutowireCapableBeanFactory beanFactory;
    private final JavaFXBuilderFactory javaFXBuilderFactory;

    @Autowired
    public SpringFxBuilderFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        javaFXBuilderFactory = new JavaFXBuilderFactory();
    }

    @SuppressWarnings("unchecked")
    public <T> FxComponentBuilder<T> getBuilder(Class<T> type) {
        Builder<T> builder = (Builder<T>) javaFXBuilderFactory.getBuilder(type);
        if(builder == null)
            return null;
        return isFxComponent(type) ? new AutowiredFxComponentBuilder<>(builder, beanFactory) : adaptBuilder(type, builder);
    }

    private <T> boolean isFxComponent(Class<T> type) {
        return type.isAnnotationPresent(FXComponent.class);
    }
}

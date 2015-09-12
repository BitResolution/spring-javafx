package com.bitresolution.spring.javafx;

import fixtures.TestApplicationContext;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApplication extends Application {

    public void start(Stage primaryStage) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationContext.class);
        SpringFxmlLoaderFactory.initialise(context);

        SpringFxmlLoader loader = SpringFxmlLoaderFactory.getLoader(getClass().getResource("test.fxml"));
        loader.load();
    }

    public static void main(String... args) {
        launch(args);
    }
}

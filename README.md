# spring-javafx
Spring Integration for JavaFX applications

# Example Usage

Checkout the project and in the root perform:

        mvn clean install

In your project POM file add the following dependency:

        <dependencies>
            ...
            <dependency>
                <groupId>com.bitresolution</groupId>
                <artifactId>spring-javafx</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
            ...
        </dependencies>
    
Then in your `Application` class do the following:

        public class MyApplication extends Application {
        
            public void start(Stage primaryStage) throws Exception {
                AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyApplicationContext.class);
                SpringFxmlLoaderFactory.initialise(context);
        
                SpringFxmlLoader loader = SpringFxmlLoaderFactory.getLoader(getClass().getResource("application.fxml"));
                loader.load();
            }
        
            public static void main(String... args) {
                launch(args);
            }
        }

Where `MyApplicationContext` is a Spring annotated configuration file, e.g.:

        @Configuration
        @ComponentScan
        @Import(SpringJavaFXConfiguration.class)
        public class MyApplicationContext {
        }

Anywhere you would have previously used `new FXMLLoader` you *must* use one of:

        SpringFxmlLoaderFactory.getLoader()
        SpringFxmlLoaderFactory.getLoader(URL location)
        
which can then be used in the same manner as `FXMLLoader`

# Autowired Controllers

See tests, more documentation coming

# FXComponents

See tests, more documentation coming

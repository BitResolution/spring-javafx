package com.bitresolution.spring.javafx

import fixtures.AnotherTestController
import fixtures.TestApplicationContext
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import spock.lang.Specification

import java.nio.charset.Charset

class SpringFMXLLoaderSpec extends Specification {

    SpringFxmlLoader springLoader
    FXMLLoader fxmlLoader
    ApplicationContext context
    URL resource
    ResourceBundle resourceBundle
    ClassLoader classloader
    Charset charset
    Node node

    def setup() {
        resource = getClass().getResource("test.fxml")
        assert resource

        context = new AnnotationConfigApplicationContext(TestApplicationContext)
        node = Mock(Node)
        resourceBundle = Mock(ResourceBundle)
        classloader = Mock(ClassLoader)
        charset = Charset.forName("UTF-16")

        SpringFxmlLoaderFactory.initialise(context)
        springLoader = SpringFxmlLoaderFactory.getLoader()
    }

    def "should throw exception if no context provided"() {
        when:
        new SpringFxmlLoader(null)

        then:
        def e = thrown(IllegalArgumentException)
        e.message.contains("ApplicationContext")
        e.message.contains("null")
    }

    def "should be able to modify Location"() {
        when:
        springLoader.setLocation(resource)

        then:
        springLoader.getLocation() == resource
    }

    def "should be able to modify Root"() {
        when:
        springLoader.setRoot(node)

        then:
        springLoader.root == node
    }

    def "should be able to modify Controller"() {
        given:
        def controller = new AnotherTestController()

        when:
        springLoader.setController(controller)

        then:
        springLoader.getController() == controller
    }
//
//    def "should be able to modify Resources"() {
//        when:
//        springLoader.setResources(resourceBundle)
//
//        then:
//    }
//
//    def "should be able to modify Charset"() {
//        when:
//        springLoader.setCharset(charset)
//
//        then:
//    }
//
//    def "should be able to modify ClassLoader"() {
//        when:
//        springLoader.setClassLoader(classloader)
//
//        then:
//    }
//
//    def "should load FXML component"() {
//        given:
//        springLoader = SpringFxmlLoaderFactory.getLoader(resource)
//
//        Application application = new Application() {
//            def fxComponent
//
//            @Override
//            void start(Stage primaryStage) throws Exception {
//                fxComponent = springLoader.load()
//            }
//
//            void start() {
//                launch()
//            }
//        }
//
//        when:
//        application.start()
//
//        then:
//        application.fxComponent.controller == context.getBean(TestController)
//    }
}

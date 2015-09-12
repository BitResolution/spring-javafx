package com.bitresolution.spring.javafx;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;
import sun.reflect.CallerSensitive;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

public class SpringFxmlLoader {

    private final ApplicationContext context;
    private final FXMLLoader loader;

    /**
     * Creates a new SpringFXMLLoader instance.
     *
     * @param context Spring application context to use to provide the controllers
     */
    public SpringFxmlLoader(ApplicationContext context) {
        this(context, null);
    }
    /**
     * Creates a new SpringFXMLLoader instance.
     *
     * @param context Spring application context to use to provide the controllers
     * @param location location of the FXML template
     */
    public SpringFxmlLoader(ApplicationContext context, URL location) {
        if(context == null) {
            throw new IllegalArgumentException("ApplicationContext can not be null");
        }
        this.loader = createFXMLLoader(location);
        this.context = context;
        this.loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                return context.getBean(param);
            }
        });
        this.loader.setBuilderFactory(context.getBean(FxBuilderRegistry.class));
    }

    private FXMLLoader createFXMLLoader(URL location) {
        return location != null ? new FXMLLoader(location) : new FXMLLoader();
    }

    /**
     * Returns the location used to resolve relative path attribute values.
     */
    public URL getLocation() {
        return loader.getLocation();
    }

    /**
     * Sets the location used to resolve relative path attribute values.
     *
     * @param location
     */
    public void setLocation(URL location) {
        loader.setLocation(location);
    }

    /**
     * Returns the namespace used by this loader.
     */
    public ObservableMap<String, Object> getNamespace() {
        return loader.getNamespace();
    }

    /**
     * Returns the root of the object hierarchy.
     */
    public <T> T getRoot() {
        return loader.getRoot();
    }

    /**
     * Sets the root of the object hierarchy. The value passed to this method
     * is used as the value of the <tt>&lt;fx:root&gt;</tt> tag. This method
     * must be called prior to loading the document when using
     * <tt>&lt;fx:root&gt;</tt>.
     *
     * @param root
     * The root of the object hierarchy.
     * @since JavaFX 2.2
     */
    public void setRoot(Object root) {
        loader.setRoot(root);
    }

    /**
     * Returns the controller associated with the root object.
     */
    public <T> T getController() {
        return loader.getController();
    }

    /**
     * Sets the controller associated with the root object. The value passed to
     * this method is used as the value of the <tt>fx:controller</tt> attribute.
     * This method must be called prior to loading the document when using
     * controller event handlers when an <tt>fx:controller</tt> attribute is not
     * specified in the document.
     *
     * @param controller
     * The controller to associate with the root object.
     * @since JavaFX 2.2
     */
    public void setController(Object controller) {
        loader.setController(controller);
    }

    /**
     * Returns the resources used to resolve resource key attribute values.
     */
    public ResourceBundle getResources() {
        return loader.getResources();
    }

    /**
     * Sets the resources used to resolve resource key attribute values.
     *
     * @param resources
     */
    public void setResources(ResourceBundle resources) {
        loader.setResources(resources);
    }

    /**
     * Returns the character set used by this loader.
     */
    public Charset getCharset() {
        return loader.getCharset();
    }

    /**
     * Sets the charset used by this loader.
     *
     * @param charset
     * @since JavaFX 2.1
     */
    public void setCharset(Charset charset) {
        loader.setCharset(charset);
    }

    /**
     * Returns the classloader used by this serializer.
     * @since JavaFX 2.1
     */
    @CallerSensitive
    public ClassLoader getClassLoader() {
        return loader.getClassLoader();
    }

    /**
     * Sets the classloader used by this serializer and clears any existing
     * imports
     *
     * @param classLoader
     * @since JavaFX 2.1
     */
    public void setClassLoader(ClassLoader classLoader) {
        loader.setClassLoader(classLoader);
    }

    /**
     * Loads an object hierarchy from a FXML document. The location from which
     * the document will be loaded must have been set by a prior call to
     * {@link #setLocation(URL)}.
     * <p>
     * When the "template" flag is set to <tt>false</tt> (the default), this
     * method will clear the imports before loading the document's content.
     * When "template" is <tt>true</tt>, the imports will not be cleared, and
     * the root value will be set to <tt>null</tt> before the content is
     * loaded. This helps improve performance on subsequent loads by
     * eliminating the overhead of loading the classes referred to by the
     * document.
     *
     * @return
     * The loaded object hierarchy.
     * @since JavaFX 2.1
     */
    @CallerSensitive
    public <T> T load() throws IOException {
        T fxComponent = loader.load();
        setRoot(fxComponent);
        return fxComponent;
    }

    /**
     * Loads an object hierarchy from a FXML document.
     *
     * @param inputStream
     * An input stream containing the FXML data to load.
     *
     * @return
     * The loaded object hierarchy.
     */
    @CallerSensitive
    public <T> T load(InputStream inputStream) throws IOException {
        return loader.load(inputStream);
    }
}

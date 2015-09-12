package fixtures;

import com.bitresolution.spring.javafx.SpringJavaFXConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(SpringJavaFXConfiguration.class)
public class TestApplicationContext {
}

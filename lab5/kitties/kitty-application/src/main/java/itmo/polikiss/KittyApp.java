package itmo.polikiss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@Import(RabbitConfig.class)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class KittyApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(KittyApp.class, args);
    }
}

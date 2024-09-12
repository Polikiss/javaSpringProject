package itmo.polikiss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@Import(RabbitConfig.class)
@SpringBootApplication
public class OwnerApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(OwnerApp.class, args);
    }
}
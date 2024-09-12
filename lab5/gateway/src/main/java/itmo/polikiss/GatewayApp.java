package itmo.polikiss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"itmo.polikiss"})
public class GatewayApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(GatewayApp.class, args);
    }
}

package itmo.polikiss;

import itmo.polikiss.models.Role;
import itmo.polikiss.services.OwnerServiceImpl;
import itmo.polikiss.services.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Date;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        OwnerServiceImpl ownerService = applicationContext.getBean(OwnerServiceImpl.class);
       // userService.createUser("Polik", "1111", Role.ADMIN, 7);
    }
}

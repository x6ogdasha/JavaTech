package Lab5.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Lab5.Dao", "Lab5.Services", "Lab5.Controllers"})
public class ownerMicroserviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ownerMicroserviceApplication.class, args);
    }
}

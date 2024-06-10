package Lab5.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Lab5.Controllers", "Lab5.Services", "Lab5.Dao"})
public class interfaceMicroserviceApplication {
    public static void main(String[] args) {

        SpringApplication.run(interfaceMicroserviceApplication.class, args);
    }
}

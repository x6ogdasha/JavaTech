package Lab5.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"Lab5.Dao.Repositories", "Cat.CatServices"})
public class catMicroserviceApplication {

    public static void main(String[] args) {

         SpringApplication.run(catMicroserviceApplication.class, args);
    }
}

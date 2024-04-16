import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"Lab3.Services", "Lab3.Controllers","Lab3.Repositories"})
public class Program {

    public static void main(String[] args)
    {
        ApplicationContext context = SpringApplication.run(Program.class, args);
    }
}

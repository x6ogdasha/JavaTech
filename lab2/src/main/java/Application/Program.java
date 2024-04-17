package Application;

import Lab3.Entities.Cat;
import Lab3.Entities.CatColor;
import Lab3.Entities.Owner;
import Lab3.Services.CatService;
import Lab3.Services.OwnerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication(scanBasePackages = {"Lab3.Services", "Lab3.Controllers","Lab3.Repositories"})
public class Program {

    public static void main(String[] args)
    {
        ApplicationContext context = SpringApplication.run(Program.class, args);
        OwnerService ownerService = context.getBean(OwnerService.class);
        CatService catService = context.getBean(CatService.class);

        Calendar date = new GregorianCalendar(2004, Calendar.FEBRUARY, 19);
        Owner owner  = new Owner("Bogdan", date);
        Owner owner2  = new Owner("Kolya", date);

        ownerService.saveOwner(owner);
        ownerService.saveOwner(owner2);

        Calendar dateOfCat = new GregorianCalendar(2022, Calendar.MARCH, 9);
        Cat cat = new Cat("MuUUUUUrzik", dateOfCat, "dvornayga", CatColor.multi);
        Cat cat2 = new Cat("Vasya", dateOfCat, "cool", CatColor.white);


        catService.saveCat(cat);
        catService.saveCat(cat2);


    }
}

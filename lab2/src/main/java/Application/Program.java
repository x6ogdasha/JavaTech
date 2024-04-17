package Application;

import Lab3.Dto.CatDto;
import Lab3.Dto.OwnerDto;
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
        OwnerDto owner  = new OwnerDto("Bogdan", date, null);
        OwnerDto owner2  = new OwnerDto("Kolya", date, null);

        ownerService.saveOwner(owner);
        ownerService.saveOwner(owner2);

        Calendar dateOfCat = new GregorianCalendar(2022, Calendar.MARCH, 9);
        CatDto cat = new CatDto("ZuUUUUUrzik", dateOfCat, "dvornayga", CatColor.multi, null, null);
        CatDto cat2 = new CatDto("Vasya", dateOfCat, "cool", CatColor.white, null, null);


        catService.saveCat(cat);
        catService.saveCat(cat2);


    }
}

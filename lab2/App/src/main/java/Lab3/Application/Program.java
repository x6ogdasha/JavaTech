package Lab3.Application;

import Lab3.Dto.CatDto;
import Lab3.Dto.OwnerDto;
import Lab3.Entities.CatColor;
import Lab3.Services.CatService;
import Lab3.Services.OwnerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

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
        OwnerDto owner  = new OwnerDto("Bogdan", "123", "ROLE_ADMIN", date, null);
        OwnerDto owner2  = new OwnerDto("Kolya", "321", "ROLE_USER", date, null);

        ownerService.saveOwner(owner);
        ownerService.saveOwner(owner2);

        Calendar dateOfCat = new GregorianCalendar(2022, Calendar.MARCH, 9);
        CatDto cat = new CatDto("Murzik", dateOfCat, "dvornayga", CatColor.multi, null, null);
        CatDto cat2 = new CatDto("Vasya", dateOfCat, "cool", CatColor.white, null, null);


        catService.saveCat(cat);
        catService.saveCat(cat2);

        catService.updateOwner(1L, 1L);
        catService.updateOwner(2L, 2L);



    }
}

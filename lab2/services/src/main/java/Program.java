import Entities.Cat;
import Entities.CatColor;
import Entities.Owner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Calendar;
import java.util.GregorianCalendar;
@SpringBootApplication
@ComponentScan(basePackages = "ru.bkitaev")
public class Program {

    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = SpringApplication.run(Program.class, args);

        OwnerService ownerService = context.getBean(OwnerService.class);
        CatService catService = context.getBean(CatService.class);


        Calendar date = new GregorianCalendar(2004, Calendar.FEBRUARY, 19);
        Owner owner  = new Owner("BOOOOOgdan", date);
        Owner owner2  = new Owner("K000000lya", date);

        ownerService.saveOwner(owner);

        Calendar dateOfCat = new GregorianCalendar(2022, Calendar.MARCH, 9);
        Cat cat = new Cat("M0000rzik", dateOfCat, "dvornayga", CatColor.multi);
        Cat cat2 = new Cat("V9999sya", dateOfCat, "cool", CatColor.white);


        catService.saveCat(cat);
        catService.saveCat(cat2);
        //catService.updateOwner(cat, owner);
        //catService.updateOwner(cat2, owner2);

        //catService.addFriendship(cat, cat2);

        context.close();

    }
}

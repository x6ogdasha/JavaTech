import Entities.Cat;
import Entities.CatColor;
import Entities.Owner;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Program {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("labb2");

        OwnerDAO ownerDAO = new OwnerDAO(emf);
        CatDAO catDAO = new CatDAO(emf);
        OwnerService ownerService = new OwnerService(ownerDAO);
        CatService catService = new CatService(catDAO);


        Calendar date = new GregorianCalendar(2004, Calendar.FEBRUARY, 19);
        Owner owner  = new Owner("Bogdan", date);
        Owner owner2  = new Owner("Kolya", date);

        ownerService.saveOwner(owner);

        Calendar dateOfCat = new GregorianCalendar(2022, Calendar.MARCH, 9);
        Cat cat = new Cat("Murzik", dateOfCat, "dvornayga", CatColor.multi);
        Cat cat2 = new Cat("Vasya", dateOfCat, "cool", CatColor.white);


        catService.saveCat(cat);
        catService.saveCat(cat2);
        catService.updateOwner(cat, owner);
        catService.updateOwner(cat2, owner2);

        catService.addFriendship(cat, cat2);

        emf.close();

    }
}

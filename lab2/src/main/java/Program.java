import Controller.Cat;
import Controller.CatColor;
import Controller.Owner;
import DAO.CatDAO;
import DAO.OwnerDAO;
import Services.CatService;
import Services.OwnerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Program {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("labb2");
        EntityManager entityManager = emf.createEntityManager();

        OwnerDAO ownerDAO = new OwnerDAO(entityManager);
        CatDAO catDAO = new CatDAO(entityManager);
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
        entityManager.close();

    }
}

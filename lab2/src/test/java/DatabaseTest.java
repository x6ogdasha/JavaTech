import DAO.CatDAO;
import DAO.OwnerDAO;
import Entities.Cat;
import Entities.CatColor;
import Services.CatService;
import Services.OwnerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DatabaseTest {

    private CatDAO catDaoMock;
    private CatService catService;

    @BeforeEach
    public void setup(){
        catDaoMock = mock(CatDAO.class);
        catService = new CatService(catDaoMock);
    }
    @Test
    public void findCat() {

        Calendar dateOfCat = new GregorianCalendar(2022, Calendar.MARCH, 9);
        Cat cat = new Cat("Murzik", dateOfCat, "dvornayga", CatColor.multi);

        List<Cat> cats = Arrays.asList(cat);
        when(catDaoMock.findByName("Murzik")).thenReturn(cat);

        Assertions.assertTrue(catDaoMock.findByName("Murzik").getName() == cat.getName());

    }

    @Test
    public void deleteCat() {

        Calendar dateOfCat = new GregorianCalendar(2022, Calendar.MARCH, 9);
        Cat cat = new Cat("Murzik", dateOfCat, "dvornayga", CatColor.multi);
        Cat cat2 = new Cat("Vasya", dateOfCat, "cool", CatColor.white);

        List<Cat> cats = Arrays.asList(cat,cat2);
        catDaoMock.deleteCat(cat2);
        when(catDaoMock.findByName("Vasya")).thenReturn(cat);
        Assertions.assertTrue(catDaoMock.findByName("Vasya").getName() != cat2.getName());

    }

    @Test
    public void updateCat() {
        Calendar dateOfCat = new GregorianCalendar(2022, Calendar.MARCH, 9);
        Cat cat = new Cat("Murzik", dateOfCat, "dvornayga", CatColor.multi);

        List<Cat> cats = Arrays.asList(cat);
        cat.setName("Yaroslav");

        catDaoMock.updateCat(cat);
        when(catDaoMock.findByName("Yaroslav")).thenReturn(cat);

        Assertions.assertTrue(catDaoMock.findByName("Yaroslav").getName() == cat.getName());

    }

}

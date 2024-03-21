package Services;

import Entities.Cat;
import Entities.Owner;
import DAO.CatDAO;

public class CatService {

    private final CatDAO catDao;

    public CatService(CatDAO catDao) {
        this.catDao = catDao;
    }

    public Cat findCat(String name) {

        return catDao.findByName(name);
    }

    public void saveCat(Cat cat) {

        catDao.saveCat(cat);
    }

    public void deleteCat(Cat cat) {

        catDao.deleteCat(cat);
    }

    public void updateCat(Cat cat) {

        catDao.updateCat(cat);
    }

    public void addFriendship(Cat cat, Cat anotherCat) {

        catDao.addFriendship(cat, anotherCat);
    }
    public void updateOwner(Cat cat, Owner owner) {

        catDao.updateOwner(cat, owner);
    }
}

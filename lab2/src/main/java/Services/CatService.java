package Services;

import Controller.Cat;
import DAO.CatDAO;

public class CatService {

    private final CatDAO catDao;

    public CatService(CatDAO catDao) {
        this.catDao = catDao;
    }

    public Cat findCat(Integer id) {

        return catDao.findById(id);
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
}

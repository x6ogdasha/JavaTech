import Entities.Cat;
import Entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {

    private final CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

//    public Cat findCat(String name) {
//
//        return catRepository.findByName(name);
//    }
    public List<Cat> getAllCats() {

        return catRepository.findAll();
    }

    public void saveCat(Cat cat) {

        catRepository.save(cat);
    }

    public void deleteCat(Cat cat) {

        catRepository.delete(cat);
    }

    public void updateCat(Cat cat) {

        catRepository.save(cat);
    }

//    public void addFriendship(Cat cat, Cat anotherCat) {
//
//        catDao.addFriendship(cat, anotherCat);
//    }
//    public void updateOwner(Cat cat, Owner owner) {
//
//        catDao.updateOwner(cat, owner);
//    }
}

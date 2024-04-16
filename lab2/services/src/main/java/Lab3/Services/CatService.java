package Lab3.Services;

import Lab3.Entities.Cat;
import Lab3.Repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void addCat(Cat cat) {
        Cat newCat = new Cat(cat.getName(),cat.getDateOfBirth(),cat.getBreed(),cat.getColor());
        catRepository.save(cat);
    }

    public void deleteCat(Long id) {
        Optional<Cat> deletingCat = catRepository.findById(id);
        if (deletingCat.isEmpty()) return;
        Cat cat = deletingCat.get();
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

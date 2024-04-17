package Lab3.Services;

import Lab3.Dto.CatDto;
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

    public Cat findCat(Long id) {

        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isEmpty()) return null;
        Cat foundCat = cat.get();
        return foundCat;
    }
    public void saveCat(CatDto cat) {

        Cat newCat = new Cat(cat.name,cat.dateOfBirth,cat.breed,cat.color);
        catRepository.save(newCat);
    }

    public void deleteCat(Long id) {

        Optional<Cat> deletingCat = catRepository.findById(id);
        if (deletingCat.isEmpty()) return;
        Cat cat = deletingCat.get();
        catRepository.delete(cat);
    }

    public void updateCat(CatDto cat) {

        Cat newCat = new Cat(cat.name,cat.dateOfBirth,cat.breed,cat.color);
        catRepository.save(newCat);
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

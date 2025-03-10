package Cat.CatServices;

import Cat.CatServices.Mappers.CatMapper;
import Lab5.Core.Dto.CatDto;
import Lab5.Core.Dto.CatColor;
import Lab5.Core.Entities.Cat;
import Lab5.Dao.Repositories.CatRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CatService {

    private final CatRepository catRepository;
    private final CatMapper catMapper = new CatMapper();

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public CatDto findCat(Long id) {

        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isEmpty()) return null;
        Cat foundCat = cat.get();

        String ownerName = catMapper.mapOwnerName(foundCat.getOwner());

        List<Long> friendsId = catMapper.mapFriendsList(foundCat);

        return new CatDto(foundCat.getName(), foundCat.getDateOfBirth(), foundCat.getBreed(), foundCat.getColor(), ownerName, friendsId);
    }
    public String saveCat(CatDto cat) {

       // Optional<Owner> owner = ownerRepository.findByName(cat.owner);
        // TODO: Подкрути пользователя, чтобы работал этот метод
//        Cat newCat;
//        newCat = owner.map(value -> new Cat(cat.name, cat.dateOfBirth, cat.breed, cat.color, value))
//                .orElseGet(() -> new Cat(cat.name, cat.dateOfBirth, cat.breed, cat.color, null));
//
//        catRepository.save(newCat);
        Cat newCat = new Cat(cat.name, cat.dateOfBirth, cat.breed, cat.color);
        newCat =  catRepository.save(newCat);
        String catId = newCat.getId().toString();

        log.info("cat persisted: {}", newCat);
        return catId;
    }

    public void deleteCat(Long id) {

        Optional<Cat> deletingCat = catRepository.findById(id);
        if (deletingCat.isEmpty()) return;
        Cat cat = deletingCat.get();
        catRepository.delete(cat);
    }

    public void updateCat(Long id, CatDto catDto) {

        Optional<Cat> catInDatabase = catRepository.findById(id);
        if (catInDatabase.isEmpty()) return;
        Cat foundCat = catInDatabase.get();
        foundCat.setName(catDto.name);
        foundCat.setDateOfBirth(catDto.dateOfBirth);
        foundCat.setBreed(catDto.breed);
        foundCat.setColor(catDto.color);
        catRepository.save(foundCat);
    }

    public void addFriendship(Long id, Long friendId) {

        Optional<Cat> catInDatabase = catRepository.findById(id);
        Optional<Cat> friendCatInDatabase = catRepository.findById(friendId);
        if (catInDatabase.isEmpty() || friendCatInDatabase.isEmpty()) return;
        catInDatabase.get().getFriends().add(friendCatInDatabase.get());

        catRepository.save(catInDatabase.get());
    }

    public List<CatDto> findCatsByColor(CatColor color) {

        List<Cat> cats = catRepository.findByColor(color);
        return catMapper.mapCatToDto(cats);
    }

}

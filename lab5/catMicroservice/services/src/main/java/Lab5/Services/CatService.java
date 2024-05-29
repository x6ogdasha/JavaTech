package Lab5.Services;

import Lab5.Services.Dto.CatDto;
//import Lab5.Dao.Dto.OwnerDto;
import Lab5.Dao.Entities.Cat;
import Lab5.Dao.Entities.CatColor;
//import Lab5.Dao.Entities.Owner;
import Lab5.Dao.Repositories.CatRepository;
//import Lab5.Dao.Repositories.OwnerRepository;
import Lab5.Services.Event.CatCreatedEvent;
import Lab5.Services.Mappers.CatMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class CatService {

    private final CatRepository catRepository;
    //private final OwnerRepository ownerRepository;
    private final CatMapper catMapper = new CatMapper();

    //TODO: подкрути пользователя
//    @Autowired
//    public CatService(CatRepository catRepository, OwnerRepository ownerRepository) {
//        this.catRepository = catRepository;
//        this.ownerRepository = ownerRepository;
//    }
    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public CatDto findCat(Long id) {

        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isEmpty()) return null;
        Cat foundCat = cat.get();

        //String ownerName = catMapper.mapOwnerName(foundCat.getOwner());

        List<Long> friendsId = catMapper.mapFriendsList(foundCat);

        return new CatDto(foundCat.getName(), foundCat.getDateOfBirth(), foundCat.getBreed(), foundCat.getColor(), "тут будет хозяин", friendsId);
    }
    public String saveCat(CatDto cat) {

       // Optional<Owner> owner = ownerRepository.findByName(cat.owner);
        // TODO: Подкрути пользователя, чтобы работал этот метод
//        Cat newCat;
//        newCat = owner.map(value -> new Cat(cat.name, cat.dateOfBirth, cat.breed, cat.color, value))
//                .orElseGet(() -> new Cat(cat.name, cat.dateOfBirth, cat.breed, cat.color, null));
//
//        catRepository.save(newCat);
        //TODO: mapper Dto -> Jpa
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
   // public String updateOwner(Long id, Long ownerId) {

//        Optional<Cat> catInDatabase = catRepository.findById(id);
//        //TODO: подкрути пользователя
//       // Optional<Owner> ownerInDatabase = ownerRepository.findById(ownerId);
//       // if (catInDatabase.isEmpty() || ownerInDatabase.isEmpty()) return null;
//        if (catInDatabase.isEmpty()) return null;
//        Cat foundCat = catInDatabase.get();
//        Owner foundOwner = ownerInDatabase.get();
//        foundCat.setOwner(foundOwner);
//        catRepository.save(foundCat);
//        return foundOwner.getName();
   // }

    public List<CatDto> findCatsByColor(CatColor color) {

        List<Cat> cats = catRepository.findByColor(color);
        return catMapper.mapCatToDto(cats);
    }

}

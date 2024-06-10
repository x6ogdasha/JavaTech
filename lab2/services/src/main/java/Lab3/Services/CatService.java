package Lab3.Services;

import Lab3.Dto.CatDto;
import Lab3.Dto.OwnerDto;
import Lab3.Entities.Cat;
import Lab3.Entities.CatColor;
import Lab3.Entities.Owner;
import Lab3.Repositories.CatRepository;
import Lab3.Repositories.OwnerRepository;
import Lab3.Services.Mappers.CatMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PostAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CatService {

    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    private final CatMapper catMapper = new CatMapper();

    @Autowired
    public CatService(CatRepository catRepository, OwnerRepository ownerRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    public CatDto findCat(Long id) {

        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isEmpty()) return null;
        Cat foundCat = cat.get();

        String ownerName = catMapper.mapOwnerName(foundCat.getOwner());

        List<Long> friendsId = catMapper.mapFriendsList(foundCat);

        return new CatDto(foundCat.getName(), foundCat.getDateOfBirth(), foundCat.getBreed(), foundCat.getColor(), ownerName, friendsId);
    }
    public void saveCat(CatDto cat) {

        Optional<Owner> owner = ownerRepository.findByName(cat.owner);
        Cat newCat;
        newCat = owner.map(value -> new Cat(cat.name, cat.dateOfBirth, cat.breed, cat.color, value))
                .orElseGet(() -> new Cat(cat.name, cat.dateOfBirth, cat.breed, cat.color, null));

        catRepository.save(newCat);
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
    public String updateOwner(Long id, Long ownerId) {

        Optional<Cat> catInDatabase = catRepository.findById(id);
        Optional<Owner> ownerInDatabase = ownerRepository.findById(ownerId);
        if (catInDatabase.isEmpty() || ownerInDatabase.isEmpty()) return null;
        Cat foundCat = catInDatabase.get();
        Owner foundOwner = ownerInDatabase.get();
        foundCat.setOwner(foundOwner);
        catRepository.save(foundCat);
        return foundOwner.getName();
    }

    public List<CatDto> findCatsByColor(CatColor color) {

        List<Cat> cats = catRepository.findByColor(color);
        return catMapper.mapCatToDto(cats);
    }

}

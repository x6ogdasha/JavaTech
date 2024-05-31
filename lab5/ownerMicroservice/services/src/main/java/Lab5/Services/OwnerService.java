package Lab5.Services;

import Lab5.Core.Dto.OwnerDto;
import Lab5.Core.Entities.Cat;
import Lab5.Core.Entities.Owner;
import Lab5.Dao.Repositories.OwnerRepository;
import Lab5.Services.Mappers.MyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final MyMapper myMapper;
    //private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    public OwnerService(OwnerRepository ownerRepository, MyMapper myMapper) {

        this.ownerRepository = ownerRepository;
        this.myMapper = myMapper;
    }

    public OwnerDto findOwner(Long id) {

        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) return null;
        Owner foundOwner = owner.get();

        List<String> ownersCats = foundOwner.getCats().stream()
                .map(Cat::getName)
                .toList();

        return new OwnerDto(foundOwner.getName(), foundOwner.getDateOfBirth(), ownersCats);
    }

    public Owner saveOwner(OwnerDto owner) {

        Owner newOwner = new Owner(owner.name,owner.dateOfBirth);
       // newOwner.setPassword(passwordEncoder.encode(owner.password));
        //newOwner.setRole(owner.role);
        ownerRepository.save(newOwner);
        log.info("owner persisted: {}", newOwner);
        return newOwner;
    }

    public void deleteOwner(Long id) {

        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) return;
        Owner foundOwner = owner.get();
        ownerRepository.delete(foundOwner);
    }
    public void updateOwner(Long id, OwnerDto ownerDto) {

        Optional<Owner> ownerInDatabase = ownerRepository.findById(id);
        if (ownerInDatabase.isEmpty()) return;
        Owner foundowner = ownerInDatabase.get();
        foundowner.setName(ownerDto.name);
        foundowner.setDateOfBirth(ownerDto.dateOfBirth);
        ownerRepository.save(foundowner);
    }


}

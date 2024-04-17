package Lab3.Services;

import Lab3.Dto.OwnerDto;
import Lab3.Entities.Cat;
import Lab3.Entities.Owner;
import Lab3.Repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public OwnerDto findOwner(Long id) {

        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) return null;
        Owner foundOwner = owner.get();
        return new OwnerDto(foundOwner.getName(), foundOwner.getDateOfBirth(), foundOwner.getCats());
    }

    public void saveOwner(OwnerDto owner) {

        Owner newOwner = new Owner(owner.name,owner.dateOfBirth);
        ownerRepository.save(newOwner);
    }

    public void deleteOwner(Long id) {

        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) return;
        Owner foundOwner = owner.get();
        ownerRepository.delete(foundOwner);
    }

    public void updateOwner(OwnerDto owner) {

        Owner newOwner = new Owner(owner.name,owner.dateOfBirth);
        ownerRepository.save(newOwner);    }


}

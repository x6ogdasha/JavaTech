package Lab3.Services;

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

    public Optional<Owner> findOwner(Long id) {

        return ownerRepository.findById(id);
    }

    public List<Owner> getAllOwners() {

        return ownerRepository.findAll();
    }

    public void saveOwner(Owner owner) {

        ownerRepository.save(owner);
    }

    public void deleteOwner(Owner owner) {

        ownerRepository.delete(owner);
    }

    public void updateOwner(Owner owner) {

        ownerRepository.save(owner);
    }


}

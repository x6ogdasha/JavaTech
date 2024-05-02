package Lab3.Services;

import Lab3.Dto.OwnerDto;
import Lab3.Entities.Cat;
import Lab3.Entities.Owner;
import Lab3.Repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public OwnerDto findOwner(Long id) {

        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) return null;
        Owner foundOwner = owner.get();

        List<String> ownersCats = foundOwner.getCats().stream()
                .map(Cat::getName)
                .toList();

        return new OwnerDto(foundOwner.getName(), foundOwner.getPassword(), foundOwner.getRole(),foundOwner.getDateOfBirth(), ownersCats);
    }

    public Owner saveOwner(OwnerDto owner) {

        Owner newOwner = new Owner(owner.name,owner.dateOfBirth);
        newOwner.setPassword(passwordEncoder.encode(owner.password));
        newOwner.setRole(owner.role);
        ownerRepository.save(newOwner);
        return newOwner;
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

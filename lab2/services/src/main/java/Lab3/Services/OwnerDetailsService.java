package Lab3.Services;

import Lab3.Entities.Owner;
import Lab3.Entities.OwnerDetails;
import Lab3.Repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerDetailsService implements UserDetailsService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Owner> owner = ownerRepository.findByName(username);

        return owner.map(OwnerDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}

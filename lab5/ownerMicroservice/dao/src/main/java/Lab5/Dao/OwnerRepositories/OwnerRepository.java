package Lab5.Dao.OwnerRepositories;

import Lab5.Core.Entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    public Optional<Owner> findByName(String name);
}

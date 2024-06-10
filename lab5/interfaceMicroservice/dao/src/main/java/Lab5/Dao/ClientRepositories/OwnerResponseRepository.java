package Lab5.Dao.ClientRepositories;

import Lab5.Core.Responses.OwnerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerResponseRepository extends JpaRepository<OwnerResponse, Long> {
}

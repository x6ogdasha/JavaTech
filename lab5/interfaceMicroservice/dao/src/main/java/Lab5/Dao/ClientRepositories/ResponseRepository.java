package Lab5.Dao.ClientRepositories;
import Lab5.Core.Responses.CatResponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<CatResponse, Long> {
}

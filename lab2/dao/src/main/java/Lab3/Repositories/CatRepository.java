package Lab3.Repositories;

import Lab3.Entities.Cat;
import Lab3.Entities.CatColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByColor(CatColor color);
}

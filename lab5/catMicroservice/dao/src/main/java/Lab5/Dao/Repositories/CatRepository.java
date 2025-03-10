package Lab5.Dao.Repositories;

import Lab5.Core.Dto.CatColor;
import Lab5.Core.Entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findByColor(CatColor color);
    Long getIdByName(String name);
}

package Lab5.Dao.ClientRepositories;

import Lab5.Core.Dto.CatDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatDtoRepository extends JpaRepository<CatDto, Long> {
}

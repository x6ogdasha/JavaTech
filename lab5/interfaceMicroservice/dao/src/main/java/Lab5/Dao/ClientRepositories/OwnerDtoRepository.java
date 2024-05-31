package Lab5.Dao.ClientRepositories;

import Lab5.Core.Dto.OwnerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDtoRepository extends JpaRepository<OwnerDto, Long> {
}

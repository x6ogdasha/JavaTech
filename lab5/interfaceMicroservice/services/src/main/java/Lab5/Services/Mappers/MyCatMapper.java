package Lab5.Services.Mappers;

import Lab5.Core.Dto.CatDto;
import Lab5.Core.Dto.CatUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class MyCatMapper {

    public CatUpdateDto convertDtoToUpdate(Long id, CatDto dto) {

        return new CatUpdateDto(id, dto.name, dto.dateOfBirth, dto.breed, dto.color, dto.owner, dto.friends);
    }
}

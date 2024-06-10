package Cat.CatServices.Mappers;

import Lab5.Core.Dto.CatDto;
import Lab5.Core.Dto.CatUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class MyMapper {

    public CatUpdateDto convertDtoToUpdate(Long id, CatDto dto) {

        return new CatUpdateDto(id, dto.name, dto.dateOfBirth, dto.breed, dto.color, dto.owner, dto.friends);
    }

    public CatDto convertUpdateDtotoDto(CatUpdateDto catUpdateDto) {

        return new CatDto(catUpdateDto.name, catUpdateDto.dateOfBirth, catUpdateDto.breed, catUpdateDto.color, catUpdateDto.owner, catUpdateDto.friends);
    }
}

package Lab5.Services.Mappers;

import Lab5.Core.Dto.OwnerDto;
import Lab5.Core.Dto.CatUpdateDto;
import Lab5.Core.Dto.OwnerUpdateDto;
import Lab5.Core.Entities.Cat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyMapper {

    public OwnerUpdateDto convertDtoToUpdate(Long id, OwnerDto dto) {

        return new OwnerUpdateDto(id, dto.name, dto.dateOfBirth, dto.cats);
    }

    public OwnerDto convertUpdateDtotoDto(OwnerUpdateDto ownerUpdateDto) {

        return new OwnerDto(ownerUpdateDto.name, ownerUpdateDto.dateOfBirth, ownerUpdateDto.cats);
    }

    public List<String> mapToStringList(List<Cat> cats) {

        List<String> catNames = new ArrayList<>();

        for(Cat foundCat : cats) {
            catNames.add(foundCat.getName());
        }

        return catNames;
    }
}

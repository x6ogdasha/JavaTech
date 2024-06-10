package Lab5.Services.Mappers;

import Lab5.Core.Dto.OwnerDto;
import Lab5.Core.Dto.CatUpdateDto;
import Lab5.Core.Dto.OwnerUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class MyOwnerMapper {

    public OwnerUpdateDto convertDtoToUpdate(Long id, OwnerDto dto) {

        return new OwnerUpdateDto(id, dto.name, dto.dateOfBirth, dto.cats);
    }

    public OwnerDto convertUpdateDtotoDto(OwnerUpdateDto ownerUpdateDto) {

        return new OwnerDto(ownerUpdateDto.name, ownerUpdateDto.dateOfBirth, ownerUpdateDto.cats);
    }
}

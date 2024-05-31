package Lab5.Core.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@Getter
public class OwnerUpdateDto {

    public Long idOwnerToUpdate;

    public String name;

    public Calendar dateOfBirth;

    public List<String> cats;
}

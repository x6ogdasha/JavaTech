package Lab5.Core.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;
@AllArgsConstructor
@Setter
@Getter
public class CatUpdateDto {
    public Long idCatToUpdate;

    public String name;

    public Calendar dateOfBirth;

    public String breed;

    public CatColor color;

    public String owner;

    public List<Long> friends;
}

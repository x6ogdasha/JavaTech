package Lab5.Domain.Dto;

import Lab5.Domain.Entities.CatColor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@Setter
public class CatDto {

    public String name;

    public Calendar dateOfBirth;

    public String breed;

    public CatColor color;

    public String owner;

    public List<Long> friends;
}

package Lab5.Services.Dto;

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

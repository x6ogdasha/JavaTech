package Lab3.Dto;

import Lab3.Entities.Cat;
import Lab3.Entities.CatColor;
import Lab3.Entities.Owner;
import lombok.AllArgsConstructor;

import java.util.Calendar;
import java.util.List;
@AllArgsConstructor
public class CatDto {
    public String name;
    public Calendar dateOfBirth;
    public String breed;
    public CatColor color;
    public Owner owner;
    public List<Cat> friends;

}

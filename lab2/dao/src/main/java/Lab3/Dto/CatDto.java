package Lab3.Dto;

import Lab3.Entities.Cat;
import Lab3.Entities.CatColor;
import Lab3.Entities.Owner;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    public Owner owner;
    public List<Cat> friends;

}

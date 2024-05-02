package Lab3.Dto;

import Lab3.Entities.Cat;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;
@AllArgsConstructor
@Setter
public class OwnerDto {

    public String name;

    public String password;

    public String role;

    public Calendar dateOfBirth;

    public List<String> cats;
}

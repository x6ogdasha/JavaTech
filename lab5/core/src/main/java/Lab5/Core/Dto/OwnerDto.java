package Lab5.Core.Dto;

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

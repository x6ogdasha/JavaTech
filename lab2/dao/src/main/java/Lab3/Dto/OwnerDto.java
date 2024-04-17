package Lab3.Dto;

import Lab3.Entities.Cat;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@AllArgsConstructor
public class OwnerDto {
    public String name;
    public Calendar dateOfBirth;
    public List<Cat> cats;
}

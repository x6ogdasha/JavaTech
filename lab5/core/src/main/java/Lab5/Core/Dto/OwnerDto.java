package Lab5.Core.Dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@Setter
@Entity
@Getter
@NoArgsConstructor
public class OwnerDto {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public String name;

    //public String password;

//    public String role;

    public Calendar dateOfBirth;

    @ElementCollection
    public List<String> cats;

    public OwnerDto(String name, Calendar dateOfBirth, List<String> cats) {

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.cats = cats;
    }
}

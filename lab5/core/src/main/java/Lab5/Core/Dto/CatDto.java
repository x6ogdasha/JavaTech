package Lab5.Core.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@Setter
@Entity
@NoArgsConstructor
public class CatDto {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("birth")
    public Calendar dateOfBirth;

    @JsonProperty("breed")
    public String breed;

    @JsonProperty("color")
    public CatColor color;

    @JsonProperty("owner")
    public String owner;

    @ElementCollection
    @JsonProperty("friends")
    public List<Long> friends;

    public CatDto(String name, Calendar dateOfBirth, String breed, CatColor color, String owner, List<Long> friends) {

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
        this.friends = friends;
    }
}

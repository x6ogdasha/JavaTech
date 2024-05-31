package Lab5.Core.Entities;

import Lab5.Core.Dto.CatColor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="cats")
public class Cat {
//TODO: верни хозяина
    @Id
    @GeneratedValue
    Long id;
    @Column
    String name;
    @Column
    Calendar dateOfBirth;
    @Column
    String breed;
    @Column
    CatColor color;
//    @ManyToOne(cascade = CascadeType.ALL)
    //Owner owner;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    List<Cat> friends = new ArrayList<>();

    public Cat(){}

//    public Cat(String name, Calendar dateOfBirth, String breed, CatColor color, Owner owner) {
//        this.name = name;
//        this.dateOfBirth = dateOfBirth;
//        this.breed = breed;
//        this.color = color;
//        this.owner = owner;
//    }
    public Cat(String name, Calendar dateOfBirth, String breed, CatColor color) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.color = color;
    }

}

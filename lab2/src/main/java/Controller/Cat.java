package Controller;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Setter
@Entity
@Table(name="cats")
public class Cat {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private Calendar dateOfBirth;
    @Column
    private String breed;
    @Column
    private CatColor color;
    @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<Cat> friends = new ArrayList<>();

    public Cat(){}

    public Cat(String name, Calendar dateOfBirth, String breed, CatColor color) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.color = color;
    }

    public void addFriend(Cat cat){

        friends.add(cat);
    }


}

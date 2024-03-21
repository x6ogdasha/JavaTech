package Controller;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.List;

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
    @JoinColumn(name = "ownerId", nullable = false)
    private Owner owner;
    //@Column @ManyToMany
    //private List<Cat> cats;

    public Cat(){}

    public Cat(String name, Calendar dateOfBirth, String breed, CatColor color) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.color = color;
    }


}

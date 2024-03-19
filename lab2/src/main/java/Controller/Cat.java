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
    @Column @OneToOne
    private Owner owner;
    @Column @ManyToMany
    private List<Cat> cats;


}

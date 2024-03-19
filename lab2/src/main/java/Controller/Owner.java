package Controller;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.List;
@Entity
public class Owner {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private Calendar dateOfBirth;
    @OneToMany
    private List<Cat> cats;
}

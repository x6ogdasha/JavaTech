package Controller;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.List;
@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private Calendar dateOfBirth;
    @OneToMany(mappedBy = "owner")
    private List<Cat> cats;

    public Owner(){
    }
    public Owner(String name, Calendar dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }
}

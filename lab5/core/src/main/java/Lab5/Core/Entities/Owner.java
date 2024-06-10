package Lab5.Core.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private Calendar dateOfBirth;
    @Column
    private String role;
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cats_of_hosts",
            joinColumns = @JoinColumn(name = "host_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id")
    )
    private List<Cat> cats = new ArrayList<>();

    public Owner(){
    }
    public Owner(String name, Calendar dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

//    public void addCat(Cat cat) {
//        if (cats == null) {
//            cats = new ArrayList<>();
//        }
//        cats.add(cat);
//        cat.setOwner(this);
//    }
}

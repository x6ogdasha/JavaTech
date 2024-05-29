package Lab5.Services.Event;

import Lab5.Dao.Entities.CatColor;
import lombok.AllArgsConstructor;

import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
public class CatCreatedEvent {
    public String catId;

    public String name;

    public Calendar dateOfBirth;

    public String breed;

    public CatColor color;

    public String owner;

    public List<Long> friends;

    public CatCreatedEvent() {}
}

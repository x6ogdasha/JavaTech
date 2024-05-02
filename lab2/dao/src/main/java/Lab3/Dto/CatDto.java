package Lab3.Dto;

import Lab3.Dto.View.BasicView;
import Lab3.Dto.View.DetailedView;
import Lab3.Entities.CatColor;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.List;
@AllArgsConstructor
@Setter
public class CatDto {

    @JsonView(BasicView.class)
    public String name;
    @JsonView(BasicView.class)
    public Calendar dateOfBirth;
    @JsonView(BasicView.class)
    public String breed;
    @JsonView(BasicView.class)
    public CatColor color;
    @JsonView(BasicView.class)
    public String owner;
    @JsonView(DetailedView.class)
    public List<Long> friends;
}

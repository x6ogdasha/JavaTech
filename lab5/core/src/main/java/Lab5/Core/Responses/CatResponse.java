package Lab5.Core.Responses;

import Lab5.Core.Dto.CatDto;
import Lab5.Core.Entities.Cat;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cat_responses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CatResponse {

    @Id
  /// @GeneratedValue(strategy= GenerationType.AUTO)
    public Long requestId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cats_for_request",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "cat_id")

    )
    private List<CatDto> catsList = new ArrayList<>();

    @JsonCreator
    public static CatResponse Create(String jsonString) throws JsonProcessingException {

        CatResponse response = null;

        ObjectMapper mapper = new ObjectMapper();
        response = mapper.readValue(jsonString, CatResponse.class);


        return response;
    }

}

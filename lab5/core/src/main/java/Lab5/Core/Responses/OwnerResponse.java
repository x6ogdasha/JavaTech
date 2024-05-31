package Lab5.Core.Responses;

import Lab5.Core.Dto.CatDto;
import Lab5.Core.Dto.OwnerDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner_responses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OwnerResponse {

    @Id
   /// @GeneratedValue(strategy= GenerationType.AUTO)
    private Long requestId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "owners_for_request",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id")

    )
    private List<OwnerDto> ownersList = new ArrayList<>();

    @JsonCreator
    public static OwnerResponse Create(String jsonString) throws JsonProcessingException {

        OwnerResponse response = null;

        ObjectMapper mapper = new ObjectMapper();
        response = mapper.readValue(jsonString, OwnerResponse.class);

        return response;
    }

}

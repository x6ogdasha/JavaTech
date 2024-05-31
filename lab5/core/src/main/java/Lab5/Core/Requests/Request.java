package Lab5.Core.Requests;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Requests")
@NoArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private RequestStatus status;

    public Request(RequestStatus status) {
        this.status = status;
    }
}

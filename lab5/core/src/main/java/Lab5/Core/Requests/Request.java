package Lab5.Core.Requests;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @GeneratedValue
    private Long requestId;
    private RequestStatus status;

    public Request(RequestStatus status) {
        this.status = status;
    }
}

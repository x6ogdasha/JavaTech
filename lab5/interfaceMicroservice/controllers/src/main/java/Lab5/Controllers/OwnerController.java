package Lab5.Controllers;

import Lab5.Core.Dto.OwnerDto;
import Lab5.Core.Events.EventById;
import Lab5.Core.Requests.Request;
import Lab5.Core.Requests.RequestStatus;
import Lab5.Core.Responses.OwnerResponse;
import Lab5.Services.OwnerService;
import Lab5.Services.ResultService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;
    private final ResultService resultService;

    @Autowired
    public OwnerController(OwnerService ownerService, ResultService resultService) {
        this.ownerService = ownerService;
        this.resultService = resultService;
    }
     @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getOwner(@RequestParam Long id) throws JsonProcessingException {

        Request request = resultService.addRequest();
        EventById eventById = new EventById(request.getId(), id);

        ownerService.getOwner(eventById);
         return new ResponseEntity<>("check result there: /owner/request/" + request.getId(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/new")
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void addOwner(@RequestBody OwnerDto owner) throws JsonProcessingException {

        ownerService.saveOwner(owner);
    }

    @PutMapping
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void updateOwner(@PathVariable Long id, @RequestBody OwnerDto owner) throws JsonProcessingException {

        ownerService.updateOwner(id,owner);
    }

    @DeleteMapping
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteOwner(@PathVariable Long id) throws JsonProcessingException {

        ownerService.deleteOwner(id);
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<?> getByRequestId(@PathVariable Long id) throws Exception {

        Request request = resultService.getRequestById(id);

        if (request.getStatus() == RequestStatus.IN_WORK) {
            return new ResponseEntity<>(
                    "go there: /cats/request/" + request.getId(),
                    HttpStatus.ACCEPTED
            );
        }

        OwnerResponse response = resultService.getOwnerResponseById(id);
        List<OwnerDto> owners = response.getOwnersList();

        return new ResponseEntity<>(
                owners,
                HttpStatus.OK
        );
    }
}

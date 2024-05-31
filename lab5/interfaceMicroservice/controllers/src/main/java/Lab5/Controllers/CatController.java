package Lab5.Controllers;

import Lab5.Controllers.Exceptions.CatNotFoundException;
import Lab5.Core.Events.EventById;
import Lab5.Core.Requests.Request;
import Lab5.Core.Requests.RequestStatus;
import Lab5.Core.Responses.CatResponse;
import Lab5.Services.CatService;
import Lab5.Core.Dto.CatDto;
import Lab5.Services.ResultService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatService catService;
    private final ResultService resultService;

    @Autowired
    public CatController(CatService catService, ResultService resultService) {
        this.catService = catService;
        this.resultService = resultService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)

   // @PostAuthorize("(returnObject.body.owner == principal.username) || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getCat(@RequestParam Long id) throws CatNotFoundException, JsonProcessingException {

        Request request = resultService.addRequest();
        EventById eventById = new EventById(request.getId(), id);
        catService.getCat(eventById);

        return new ResponseEntity<>("check result there: /cat/request/" + request.getId(), HttpStatus.ACCEPTED);
    }
    @PostMapping
    public void addCat(@RequestBody CatDto cat) throws JsonProcessingException {

        catService.saveCat(cat);
    }

    @PutMapping
    public void updateCat(@RequestParam Long id, @RequestBody CatDto catDto) throws JsonProcessingException {

        catService.updateCat(id, catDto);
    }

    @DeleteMapping
    public void deleteCat(@RequestParam Long id) throws JsonProcessingException {

        catService.deleteCat(id);
    }
//    @PutMapping("/newOwner")
//    public void updateOwner(@RequestParam Long id, @RequestParam Long ownerId) {
//
//        String newOwner = catService.updateOwner(id, ownerId);
//    }
//    @PutMapping("/newFriend")
//    public void addFriendship(@RequestParam Long id, @RequestParam Long friendId) {
//
//        catService.addFriendship(id, friendId);
//        catService.addFriendship(friendId, id);
//    }
//
//    @GetMapping("/byColor")
//   // @PostFilter("hasAuthority('ROLE_ADMIN') or authentication.name == filterObject.owner")
//    public List<CatDto> findCatByColor(@RequestParam CatColor color) throws CatNotFoundException {
//
//        List<CatDto> cats = catService.findCatsByColor(color);
//        if (cats.isEmpty()) throw new CatNotFoundException("no cats with this color");
//
//        return catService.findCatsByColor(color);
//    }

    @GetMapping("/request/{id}")
    public ResponseEntity<?> getByRequestId(@PathVariable Long id) throws Exception {
        Request request = resultService.getRequestById(id);


        if (request.getStatus() == RequestStatus.IN_WORK) {
            return new ResponseEntity<>(
                    "go there: /cats/request/" + request.getId(),
                    HttpStatus.ACCEPTED
            );
        }

        CatResponse response = resultService.getResponseById(id);
        List<CatDto> cats = response.getCatsList();

        return new ResponseEntity<>(
                cats,
                HttpStatus.OK
        );
    }

}

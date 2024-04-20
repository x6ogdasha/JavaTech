package Lab3.Controllers;

import Lab3.Dto.CatDto;
import Lab3.Dto.View.BasicView;
import Lab3.Entities.CatColor;
import Lab3.Services.CatService;
import com.fasterxml.jackson.annotation.JsonView;
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

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(BasicView.class)
    public ResponseEntity<CatDto> getCat(@RequestParam Long id) {

        CatDto cat = catService.findCat(id);
        return ResponseEntity.ok(cat);
    }
    @PostMapping
    @JsonView(BasicView.class)
    public ResponseEntity<String> addCat(@RequestBody CatDto cat) {

        catService.saveCat(cat);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cat added successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateCat(@RequestParam Long id, @RequestBody CatDto catDto) {

        catService.updateCat(id, catDto);
        return ResponseEntity.ok("Cat modified successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCat(@RequestParam Long id) {

        catService.deleteCat(id);
        return ResponseEntity.ok("Cat deleted successfully");
    }
    @PutMapping("/newOwner")
    public ResponseEntity<String> updateOwner(@RequestParam Long id, @RequestParam Long ownerId) {

        String newOwner = catService.updateOwner(id, ownerId);
        return ResponseEntity.ok("Owner updated successfully. New owner: " + newOwner);
    }
    @PutMapping("/newFriend")
    public ResponseEntity<String> addFriendship(@RequestParam Long id, @RequestParam Long friendId) {

        catService.addFriendship(id, friendId);
        catService.addFriendship(friendId, id);

        return ResponseEntity.ok("Friendship created");
    }

    @GetMapping("/byColor")
    public ResponseEntity<List<CatDto>> findCatByColor(@RequestParam CatColor color) {

        return ResponseEntity.ok(catService.findCatsByColor(color));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {

        return ResponseEntity.ok("Hello");
    }
}
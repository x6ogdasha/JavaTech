package Lab3.Controllers;

import Lab3.Controllers.Exceptions.CatNotFoundException;
import Lab3.Dto.CatDto;
import Lab3.Entities.CatColor;
import Lab3.Services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
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
    @PostAuthorize("(returnObject.body.owner == principal.username) || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CatDto> getCat(@RequestParam Long id) throws CatNotFoundException {

        CatDto cat = catService.findCat(id);
        if (cat == null) throw new CatNotFoundException("No this cat ");
        return ResponseEntity.ok(cat);
    }
    @PostMapping
    public void addCat(@RequestBody CatDto cat) {

        catService.saveCat(cat);
    }

    @PutMapping
    public void updateCat(@RequestParam Long id, @RequestBody CatDto catDto) {

        catService.updateCat(id, catDto);
    }

    @DeleteMapping
    public void deleteCat(@RequestParam Long id) {

        catService.deleteCat(id);
    }
    @PutMapping("/newOwner")
    public void updateOwner(@RequestParam Long id, @RequestParam Long ownerId) {

        String newOwner = catService.updateOwner(id, ownerId);
    }
    @PutMapping("/newFriend")
    public void addFriendship(@RequestParam Long id, @RequestParam Long friendId) {

        catService.addFriendship(id, friendId);
        catService.addFriendship(friendId, id);
    }

    @GetMapping("/byColor")
    @PostFilter("hasAuthority('ROLE_ADMIN') or authentication.name == filterObject.owner")
    public List<CatDto> findCatByColor(@RequestParam CatColor color) throws CatNotFoundException {

        List<CatDto> cats = catService.findCatsByColor(color);
        if (cats.isEmpty()) throw new CatNotFoundException("no cats with this color");

        return catService.findCatsByColor(color);
    }

}

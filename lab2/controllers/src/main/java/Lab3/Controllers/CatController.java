package Lab3.Controllers;

import Lab3.Dto.CatDto;
import Lab3.Entities.Cat;
import Lab3.Services.CatService;
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
    public ResponseEntity<CatDto> getCat(@RequestParam Long id) {

        CatDto cat = catService.findCat(id);
        return ResponseEntity.ok(cat);
    }
    @PostMapping
    public ResponseEntity<String> addCat(@RequestBody CatDto cat) {

        catService.saveCat(cat);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cat added successfully");
    }

    @PutMapping
    public ResponseEntity<String> modifyCat(@RequestBody CatDto cat) {

        catService.updateCat(cat);
        return ResponseEntity.ok("Cat modified successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCat(@RequestParam Long id) {

        catService.deleteCat(id);
        return ResponseEntity.ok("Cat deleted successfully");
    }
}

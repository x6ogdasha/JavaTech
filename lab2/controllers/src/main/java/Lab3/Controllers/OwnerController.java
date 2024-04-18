package Lab3.Controllers;

import Lab3.Dto.OwnerDto;
import Lab3.Entities.Owner;
import Lab3.Services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OwnerDto> getOwner(@RequestParam Long id) {

        OwnerDto owner = ownerService.findOwner(id);
        return ResponseEntity.ok(owner);
    }

    @PostMapping
    public void addOwner(@RequestBody OwnerDto owner) {

        ownerService.saveOwner(owner);
    }

    @PutMapping
    public void updateOwner(@PathVariable String name, @RequestBody OwnerDto owner) {

        owner.setName(name);
        ownerService.updateOwner(owner);
    }

    @DeleteMapping
    public void deleteOwner(@PathVariable Long id) {

        ownerService.deleteOwner(id);
    }

}

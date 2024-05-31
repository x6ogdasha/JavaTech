package Lab5.Controllers;

import Lab5.Core.Dto.OwnerDto;
import Lab5.Services.OwnerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<OwnerDto> getOwner(@RequestParam Long id) {
//
//        OwnerDto owner = ownerService.findOwner(id);
//        return ResponseEntity.ok(owner);
//    }

    @PostMapping("/new")
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void addOwner(@RequestBody OwnerDto owner) throws JsonProcessingException {

        ownerService.saveOwner(owner);
    }

   // @PutMapping
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public void updateOwner(@PathVariable String name, @RequestBody OwnerDto owner) {
//
//        owner.setName(name);
//        ownerService.updateOwner(owner);
//    }

   // @DeleteMapping
   // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public void deleteOwner(@PathVariable Long id) {
//
//        ownerService.deleteOwner(id);
//    }

}

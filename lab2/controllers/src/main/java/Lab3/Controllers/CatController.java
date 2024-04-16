package Lab3.Controllers;

import Lab3.Entities.Cat;
import Lab3.Services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class CatController {
    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/cats")
    public List<Cat> getAllCats() {

        return catService.getAllCats();
    }

    @GetMapping("/")
    public String test(){
        return "Hello";
    }
}

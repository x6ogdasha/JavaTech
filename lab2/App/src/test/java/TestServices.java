import Lab3.Application.Program;
import Lab3.Dto.CatDto;
import Lab3.Dto.OwnerDto;
import Lab3.Entities.Cat;
import Lab3.Entities.CatColor;
import Lab3.Entities.Owner;
import Lab3.Repositories.CatRepository;
import Lab3.Repositories.OwnerRepository;
import Lab3.Services.CatService;
import Lab3.Services.OwnerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = Program.class)
public class TestServices {

    @Mock
    private CatRepository catRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private CatService catService;

    @InjectMocks
    private OwnerService ownerService;


    @org.junit.jupiter.api.Test
    public void testCatService() {

        Cat cat = new Cat();
        cat.setName("Test Cat");
        cat.setDateOfBirth(Calendar.getInstance());
        cat.setBreed("Test Breed");
        cat.setColor(CatColor.brown);

        when(catRepository.findById(1L)).thenReturn(Optional.of(cat));

        CatDto catDto = catService.findCat(1L);

        assertNotNull(catDto);
    }

    @org.junit.jupiter.api.Test
    public void testOwnerService() {

        Owner owner = new Owner();
        owner.setName("test");
        owner.setDateOfBirth(Calendar.getInstance());
        owner.setCats(null);

        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));

        OwnerDto ownerDto = ownerService.findOwner(1L);

        assertNotNull(ownerDto);
    }

}

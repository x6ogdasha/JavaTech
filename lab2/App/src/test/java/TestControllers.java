import Lab3.Application.Program;
import Lab3.Controllers.CatController;
import Lab3.Controllers.Exceptions.CatNotFoundException;
import Lab3.Controllers.OwnerController;
import Lab3.Dto.CatDto;
import Lab3.Dto.OwnerDto;
import Lab3.Entities.Cat;
import Lab3.Entities.CatColor;
import Lab3.Entities.Owner;
import Lab3.Repositories.CatRepository;
import Lab3.Repositories.OwnerRepository;
import Lab3.Services.CatService;
import Lab3.Services.OwnerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Program.class)
public class TestControllers {

    @Mock
    private CatRepository catRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private CatService catService;

    @Mock
    private OwnerService ownerService;


    @Test
    public void testCatController() throws CatNotFoundException {

        CatController catController = new CatController(catService);

        Cat cat = new Cat();
        cat.setName("Test Cat");
        cat.setDateOfBirth(Calendar.getInstance());
        cat.setBreed("Test Breed");
        cat.setColor(CatColor.brown);

        CatDto catdto = new CatDto(
                "Test",
                Calendar.getInstance(),
                "breed",
                CatColor.black,
                null,
                null);

        when(catRepository.findById(1L)).thenReturn(Optional.of(cat));
        doReturn(catdto).when(catService).findCat(1L);

        ResponseEntity<CatDto> response = catController.getCat(1L);

        assertTrue(response.getStatusCode().equals(HttpStatus.OK));

    }

    @Test
    public void testOwnerController() {

        OwnerController ownerController = new OwnerController(ownerService);

        Owner owner = new Owner();
        owner.setName("test");
        owner.setDateOfBirth(Calendar.getInstance());
        owner.setCats(null);

        OwnerDto ownerdto = new OwnerDto(
               "test",
               "123",
               "ROLE_ADMIN",
               Calendar.getInstance(),
                null
        );

        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
        doReturn(ownerdto).when(ownerService).findOwner(1L);

        ResponseEntity<OwnerDto> response = ownerController.getOwner(1L);

        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

}

import Lab3.Application.Program;
import Lab3.Controllers.OwnerController;
import Lab3.Dto.CatDto;
import Lab3.Dto.OwnerDto;
import Lab3.Entities.CatColor;
import Lab3.Repositories.OwnerRepository;
import Lab3.Services.CatService;
import Lab3.Services.OwnerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Program.class)
public class TestSecurity {

    @Autowired
    OwnerService ownerService;

    @Autowired
    OwnerController ownerController;

    @Autowired
    CatService catService;

    @Mock
    OwnerRepository ownerRepository;

    @Test
    @WithMockUser
    public void testAdmin() {

        OwnerDto owner  = new OwnerDto("Bogdan", "123", "ROLE_ADMIN", Calendar.getInstance(), null);

        var newOwner = ownerService.saveOwner(owner);

        assertNotNull(newOwner.getId());

    }

    @Test
    @WithMockUser
    public void testPreAuthorize() {

        OwnerDto owner  = new OwnerDto("Bogdan2", "123", "ROLE_USER", Calendar.getInstance(), null);

        assertThrows(AccessDeniedException.class, () -> this.ownerController.addOwner(owner));
    }

}

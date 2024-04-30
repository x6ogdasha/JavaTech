import Lab3.Application.Program;
import Lab3.Dto.OwnerDto;
import Lab3.Repositories.OwnerRepository;
import Lab3.Services.OwnerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = Program.class)
public class TestSecurity {

    @Autowired
    OwnerService ownerService;

    @Mock
    OwnerRepository ownerRepository;

    @Test
    @WithMockUser(roles = "admin")
    public void testAdmin() {

        OwnerDto owner  = new OwnerDto("Bogdan", Calendar.getInstance(), null);

        var newOwner = ownerService.saveOwner(owner);

        assertNotNull(newOwner.getId());

    }

    @Test
    @WithMockUser
    public void testPreAuthorize() {

        OwnerDto owner  = new OwnerDto("Bogdan", Calendar.getInstance(), null);

        assertThrows(AccessDeniedException.class, () -> this.ownerService.saveOwner(owner));
    }
}

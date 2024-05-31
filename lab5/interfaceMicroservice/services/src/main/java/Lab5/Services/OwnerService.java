package Lab5.Services;

import Lab5.Core.Dto.OwnerDto;
import Lab5.Services.Producers.OwnerProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerProducer ownerProducer;

    @Autowired
    public OwnerService(OwnerProducer ownerProducer) {
        this.ownerProducer = ownerProducer;
    }

    public String saveOwner(OwnerDto owner) throws JsonProcessingException {

        return ownerProducer.sendMessage(owner);
    }
}

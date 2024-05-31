package Lab5.Services.Producers;

import Lab5.Core.Dto.OwnerDto;
import Lab5.Core.Dto.OwnerUpdateDto;
import Lab5.Core.Events.EventById;
import Lab5.Services.Mappers.MyCatMapper;
import Lab5.Services.Mappers.MyOwnerMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OwnerProducer {

    private final ObjectMapper objectMapper;
    private final MyOwnerMapper myMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public OwnerProducer(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper, MyOwnerMapper myMapper) {

        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.myMapper = myMapper;
    }

    public String sendForAddingOwnerMessage(OwnerDto ownerDto) throws JsonProcessingException {

        String dtoAsMessage = objectMapper.writeValueAsString(ownerDto);
        kafkaTemplate.send("owner-add-events-topic", dtoAsMessage);
        log.info("cat adding event produced {}", dtoAsMessage);

        return "message sent";
    }

    public String sendForGettingOwnerMessage(EventById event) throws JsonProcessingException {

        String message = objectMapper.writeValueAsString(event);
        kafkaTemplate.send("owner-get-events-topic", message);
        log.info("owner getting event produced {}", message);

        return "message sent";
    }
    public String sendUpdatingOwnerMessage(Long id, OwnerDto ownerdto) throws JsonProcessingException {

        OwnerUpdateDto catUpdateDto = myMapper.convertDtoToUpdate(id,ownerdto);
        String dtoAsMessage = objectMapper.writeValueAsString(catUpdateDto);
        kafkaTemplate.send("owner-update-events-topic", dtoAsMessage);
        log.info("owner updating event produced {}", dtoAsMessage);

        return "message sent";
    }
    public String sendDeletingCatMessage(Long id) throws JsonProcessingException {

        String idAsMessage = objectMapper.writeValueAsString(id);
        kafkaTemplate.send("owner-delete-events-topic", idAsMessage);
        log.info("owner deleting event produced {}", idAsMessage);

        return "message sent";
    }
}
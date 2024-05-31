package Lab5.Services;

import Lab5.Core.Dto.CatDto;
import Lab5.Core.Dto.CatUpdateDto;
import Lab5.Core.Dto.OwnerDto;
import Lab5.Core.Dto.OwnerUpdateDto;
import Lab5.Core.Events.EventById;
import Lab5.Core.Responses.CatResponse;
import Lab5.Core.Responses.OwnerResponse;
import Lab5.Services.Mappers.MyMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class OwnerConsumer {

    private final ObjectMapper objectMapper;
    private final OwnerService ownerService;

    private final MyMapper myMapper;

    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Autowired
    public OwnerConsumer(ObjectMapper objectMapper, OwnerService ownerService, MyMapper myMapper, KafkaTemplate<String, Object> kafkaTemplate) {

        this.objectMapper = objectMapper;
        this.ownerService = ownerService;
        this.myMapper = myMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "owner-add-events-topic")
    public void consumeForAddingOwnerMessage(String message) throws JsonProcessingException {

        log.info("message consumed: {}", message);

        //TODO: добавить исключение в Advice Controller
        OwnerDto ownerDto = objectMapper.readValue(message, OwnerDto.class);
        ownerService.saveOwner(ownerDto);
    }

    @KafkaListener(topics = "owner-get-events-topic")
    public void consumeForGettingOwnerMessage(String message) throws JsonProcessingException {

        log.info("message consumed: {}", message);

        //TODO: добавить исключение в Advice Controller
        EventById eventById = objectMapper.readValue(message, EventById.class);
        log.info("event from mapper: {}", eventById.catId);
        OwnerDto ownerDto = ownerService.findOwner(eventById.catId);
        log.info("found dto: {}", ownerDto.name);


        List<OwnerDto> listOfOwners = new ArrayList<>();
        listOfOwners.add(ownerDto);

        OwnerResponse ownerResponse = new OwnerResponse(eventById.id, listOfOwners);
        String responseMessage = objectMapper.writeValueAsString(ownerResponse);
        kafkaTemplate.send("owner-result-topic", responseMessage);
        log.info("message sent back (from get): {}", responseMessage);
    }
    @KafkaListener(topics = "owner-delete-events-topic")
    public void consumeForDeletingCatMessage(String message) throws JsonProcessingException {

        log.info("message consumed: {}", message);

        //TODO: добавить исключение в Advice Controller
        Long id = objectMapper.readValue(message, Long.class);
        ownerService.deleteOwner(id);
    }
    @KafkaListener(topics = "owner-update-events-topic")
    public void consumeForUpdatingCatMessage(String message) throws JsonProcessingException {

        log.info("message consumed: {}", message);

        //TODO: добавить исключение в Advice Controller
        OwnerUpdateDto ownerUpdateDto = objectMapper.readValue(message, OwnerUpdateDto.class);
        OwnerDto ownerDto = myMapper.convertUpdateDtotoDto(ownerUpdateDto);
        ownerService.updateOwner(ownerUpdateDto.getIdOwnerToUpdate(), ownerDto);
    }
}

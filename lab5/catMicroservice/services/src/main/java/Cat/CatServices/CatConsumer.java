package Cat.CatServices;

import Cat.CatServices.Mappers.MyMapper;
import Lab5.Core.Dto.CatUpdateDto;
import Lab5.Core.Events.EventById;
import Lab5.Core.Responses.CatResponse;
import Lab5.Core.Dto.CatDto;
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
public class CatConsumer {

    private final ObjectMapper objectMapper;
    private final CatService catService;

    private final MyMapper myMapper;

    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Autowired
    public CatConsumer(ObjectMapper objectMapper, CatService catService, MyMapper myMapper, KafkaTemplate<String, Object> kafkaTemplate) {

        this.objectMapper = objectMapper;
        this.catService = catService;
        this.myMapper = myMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "cat-add-events-topic")
    public void consumeForAddingCatMessage(String message) throws JsonProcessingException {

        log.info("message consumed: {}", message);

        //TODO: добавить исключение в Advice Controller
        CatDto catDto = objectMapper.readValue(message, CatDto.class);
        catService.saveCat(catDto);
    }

    @KafkaListener(topics = "cat-get-events-topic")
    public void consumeForGettingCatMessage(String message) throws JsonProcessingException {

        log.info("message consumed: {}", message);

        //TODO: добавить исключение в Advice Controller
        EventById eventById = objectMapper.readValue(message, EventById.class);
        log.info("event from mapper: {}", eventById.catId);
        CatDto catDto = catService.findCat(eventById.catId);
        log.info("found dto: {}", catDto.name);


        List<CatDto> listOfCats = new ArrayList<>();
        listOfCats.add(catDto);

        CatResponse catResponse = new CatResponse(eventById.id, listOfCats);
        String responseMessage = objectMapper.writeValueAsString(catResponse);
        kafkaTemplate.send("cat-result-topic", responseMessage);
        log.info("message sent back (from get): {}", responseMessage);
    }
    @KafkaListener(topics = "cat-delete-events-topic")
    public void consumeForDeletingCatMessage(String message) throws JsonProcessingException {

        log.info("message consumed: {}", message);

        //TODO: добавить исключение в Advice Controller
        Long id = objectMapper.readValue(message, Long.class);
        catService.deleteCat(id);
    }
    @KafkaListener(topics = "cat-update-events-topic")
    public void consumeForUpdatingCatMessage(String message) throws JsonProcessingException {

        log.info("message consumed: {}", message);

        //TODO: добавить исключение в Advice Controller
        CatUpdateDto catUpdateDto = objectMapper.readValue(message, CatUpdateDto.class);
        CatDto catDto = myMapper.convertUpdateDtotoDto(catUpdateDto);
        catService.updateCat(catUpdateDto.getIdCatToUpdate(), catDto);
    }
}

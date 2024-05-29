package Lab5.Services;

import Lab5.Services.Dto.CatDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CatConsumer {

    private final ObjectMapper objectMapper;
    private final CatService catService;

    @Autowired
    public CatConsumer(ObjectMapper objectMapper, CatService catService) {

        this.objectMapper = objectMapper;
        this.catService = catService;
    }

    @KafkaListener(topics = "cat-add-events-topic")
    public void consumeMessage(String message) throws JsonProcessingException {

        log.info("message consumed: {}", message);

        //TODO: добавить исключение в Advice Controller
        CatDto catDto = objectMapper.readValue(message, CatDto.class);
        catService.saveCat(catDto);
    }
}

package Lab5.Services;

import Lab5.Services.Dto.CatDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CatProducer {

    private String eventTopic = "cat-add-events-topic";

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public CatProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessage(CatDto catDto) throws JsonProcessingException {

        String dtoAsMessage = objectMapper.writeValueAsString(catDto);
        kafkaTemplate.send(eventTopic, dtoAsMessage);
        log.info("cat event produced {}", dtoAsMessage);

        return "message sent";
    }
}

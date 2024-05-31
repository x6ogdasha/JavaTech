package Lab5.Services.Producers;

import Lab5.Core.Dto.CatDto;
import Lab5.Core.Dto.OwnerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OwnerProducer {
    private String eventTopic = "owner-add-events-topic";

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public OwnerProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessage(OwnerDto ownerDto) throws JsonProcessingException {

        String dtoAsMessage = objectMapper.writeValueAsString(ownerDto);
        kafkaTemplate.send(eventTopic, dtoAsMessage);
        log.info("owner event produced {}", dtoAsMessage);

        return "message sent";
    }
}

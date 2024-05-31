package Lab5.Services.Producers;

import Lab5.Core.Dto.CatDto;
import Lab5.Core.Dto.CatUpdateDto;
import Lab5.Core.Events.EventById;
import Lab5.Services.Mappers.MyCatMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CatProducer {

    private final ObjectMapper objectMapper;
    private final MyCatMapper myMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public CatProducer(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper, MyCatMapper myMapper) {

        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.myMapper = myMapper;
    }

    public String sendForAddingCatMessage(CatDto catDto) throws JsonProcessingException {

        String dtoAsMessage = objectMapper.writeValueAsString(catDto);
        kafkaTemplate.send("cat-add-events-topic", dtoAsMessage);
        log.info("cat adding event produced {}", dtoAsMessage);

        return "message sent";
    }

    public String sendForGettingCatMessage(EventById event) throws JsonProcessingException {

        String message = objectMapper.writeValueAsString(event);
        kafkaTemplate.send("cat-get-events-topic", message);
        log.info("cat getting event produced {}", message);

        return "message sent";
    }
    public String sendUpdatingCatMessage(Long id, CatDto dto) throws JsonProcessingException {

        CatUpdateDto catUpdateDto = myMapper.convertDtoToUpdate(id,dto);
        String dtoAsMessage = objectMapper.writeValueAsString(catUpdateDto);
        kafkaTemplate.send("cat-update-events-topic", dtoAsMessage);
        log.info("cat updating event produced {}", dtoAsMessage);

        return "message sent";
    }
    public String sendDeletingCatMessage(Long id) throws JsonProcessingException {

        String idAsMessage = objectMapper.writeValueAsString(id);
        kafkaTemplate.send("cat-delete-events-topic", idAsMessage);
        log.info("cat deleting event produced {}", idAsMessage);

        return "message sent";
    }
}
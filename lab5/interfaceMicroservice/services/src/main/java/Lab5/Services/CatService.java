package Lab5.Services;

import Lab5.Services.Dto.CatDto;
import Lab5.Services.Event.CatCreatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CatService {

    private final CatProducer catProducer;

    @Autowired
    public CatService(CatProducer catProducer) {
        this.catProducer = catProducer;
    }

    public String saveCat(CatDto cat) throws JsonProcessingException {

        return catProducer.sendMessage(cat);
    }


}

package Lab5.Services;

import Lab5.Core.Dto.CatDto;
import Lab5.Core.Requests.Request;
import Lab5.Core.Requests.RequestStatus;
import Lab5.Core.Responses.CatResponse;
import Lab5.Dao.ClientRepositories.CatDtoRepository;
import Lab5.Dao.ClientRepositories.RequestRepository;
import Lab5.Dao.ClientRepositories.ResponseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResultService {

    private final RequestRepository requestRepository;
    private final ResponseRepository responseRepository;

    private final CatDtoRepository catDtoRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ResultService(RequestRepository requestRepository, ResponseRepository responseRepository, CatDtoRepository catDtoRepository) {

        this.requestRepository = requestRepository;
        this.responseRepository = responseRepository;
        this.catDtoRepository = catDtoRepository;
    }

    public Request addRequest() {

        Request request = new Request(RequestStatus.IN_WORK);
       return requestRepository.save(request);
    }
    public void updateRequest(Request request) {
        requestRepository.save(request);
    }

    public Request getRequestById(Long id) throws Exception {
        return requestRepository.findById(id).orElseThrow( () -> new Exception("No id:  " + id));
    }
    public CatResponse getResponseById(Long id) throws Exception {
        return responseRepository.findById(id).orElseThrow(() -> new Exception("No id: " + id));
    }

    @KafkaListener(topics = "cat-result-topic", groupId = "group-id")
    public void consumeCatResult(String message) throws JsonProcessingException {

        log.info("Getting result: {}", message);
        CatResponse catResponse = objectMapper.readValue(message, CatResponse.class);
        Request request = new Request(catResponse.getRequestId(), RequestStatus.SUCCESS);
        updateRequest(request);
        List<CatDto> catDtoList = catResponse.getCatsList();

        for (CatDto cat : catDtoList) {
            catDtoRepository.save(cat);
        }


        responseRepository.save(catResponse);
    }
}

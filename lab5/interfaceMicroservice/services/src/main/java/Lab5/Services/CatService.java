package Lab5.Services;

import Lab5.Core.Dto.CatDto;
import Lab5.Core.Events.EventById;
import Lab5.Services.Producers.CatProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatService {

    private final CatProducer catProducer;

    @Autowired
    public CatService(CatProducer catProducer) {
        this.catProducer = catProducer;
    }

    public String saveCat(CatDto cat) throws JsonProcessingException {

        return catProducer.sendForAddingCatMessage(cat);
    }

    public String getCat(EventById event) throws JsonProcessingException {

        return catProducer.sendForGettingCatMessage(event);
    }

    public String updateCat(Long id, CatDto dto) throws JsonProcessingException {

        return catProducer.sendUpdatingCatMessage(id, dto);
    }

    public String deleteCat(Long id) throws JsonProcessingException {

        return catProducer.sendDeletingCatMessage(id);
    }


}

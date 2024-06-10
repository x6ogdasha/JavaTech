package Cat.CatServices.Mappers;

import Lab5.Core.Dto.CatDto;
import Lab5.Core.Entities.Cat;
import Lab5.Core.Entities.Owner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatMapper {

    public String mapOwnerName(Owner owner) {

        String ownerName = null;
        if (owner != null) ownerName = owner.getName();
        return ownerName;
    }

    public List<Long> mapFriendsList(Cat cat) {

        List<Long> friendsId = null;
        if (cat.getFriends() != null) friendsId = cat.getFriends().stream().map(Cat::getId).toList();
        return friendsId;
    }

    public List<CatDto> mapCatToDto(List<Cat> cats) {

        List<CatDto> catsDto = new ArrayList<>();

        for (Cat foundCat : cats) {

//            String ownerName = null;
//            if (foundCat.getOwner() != null) ownerName = foundCat.getOwner().getName();

            List<Long> friendsId = null;
            if (foundCat.getFriends() != null) friendsId = foundCat.getFriends().stream().map(Cat::getId).toList();

            catsDto.add(new CatDto(foundCat.getName(), foundCat.getDateOfBirth(), foundCat.getBreed(),foundCat.getColor(),"тут будет хозяин", friendsId));
        }

        return catsDto;
    }
}

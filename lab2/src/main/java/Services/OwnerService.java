package Services;

import Entities.Owner;
import DAO.OwnerDAO;

public class OwnerService {

    private final OwnerDAO ownerDao;

    public OwnerService(OwnerDAO ownerDao) {
        this.ownerDao = ownerDao;
    }

    public Owner findOwner(Integer id) {

        return ownerDao.findById(id);
    }

    public void saveOwner(Owner owner) {

        ownerDao.saveOwner(owner);
    }

    public void deleteOwner(Owner owner) {

        ownerDao.deleteOwner(owner);
    }

    public void updateOwner(Owner owner) {

        ownerDao.updateOwner(owner);
    }


}

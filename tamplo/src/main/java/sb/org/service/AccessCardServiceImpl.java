package sb.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sb.org.dao.AccessCardDAO;
import sb.org.model.AccessCard;

import java.util.List;

@Service
@Transactional
public class AccessCardServiceImpl implements AccessCardService {

    @Autowired
    private AccessCardDAO accessCardDAO;

    @Override
    @Transactional
    public void addAccessCard(AccessCard accessCard) {
        accessCardDAO.addAccessCard(accessCard);
    }

    @Override
    public List<AccessCard> getAllAccessCard() {
        return accessCardDAO.getAllAccessCard();
    }

    @Override
    public void deleteTask(Integer accessCardId) {
        accessCardDAO.deleteTask(accessCardId);
    }

    @Override
    public AccessCard getAccessCard(int accessCardId) {
        return accessCardDAO.getAccessCard(accessCardId);
    }

    @Override
    public AccessCard updateAccessCard(AccessCard accessCard) {
        return accessCardDAO.updateAccessCard(accessCard);
    }

    @Override
    public void getEmployeeId(Integer employeeId) {
        System.out.println("getEmployeeId() method Called");
        accessCardDAO.getEmployeeId(employeeId);
    }
}

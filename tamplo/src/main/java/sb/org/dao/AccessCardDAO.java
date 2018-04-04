package sb.org.dao;

import sb.org.model.AccessCard;

import java.util.List;

public interface AccessCardDAO {

    public void addAccessCard(AccessCard accessCard);

    public List<AccessCard> getAllAccessCard();

    public void deleteTask(Integer accessCardId);

    public AccessCard getAccessCard(int accessCardId);

    public AccessCard updateAccessCard(AccessCard accessCard);

    public void getEmployeeId(Integer employeeId);
}

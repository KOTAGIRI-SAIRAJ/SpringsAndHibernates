package sb.org.service;

import sb.org.model.AccessCard;

import java.util.List;

public interface AccessCardService {

    public void addAccessCard(AccessCard accessCard);

    public List<AccessCard> getAllAccessCard();

    public void deleteTask(Integer accessCardId);

    public AccessCard getAccessCard(int accessCardId);

    public AccessCard updateAccessCard(AccessCard accessCard);
}

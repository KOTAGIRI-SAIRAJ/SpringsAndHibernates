package sb.org.service;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import sb.org.dao.AccessCardDAO;
import sb.org.model.AccessCard;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class AccessCardTestService {

    @Mock
    AccessCardDAO accessCardDAO;

    @InjectMocks
    AccessCardServiceImpl accessCardService;

    @Spy
    List<AccessCard> accessCards = new ArrayList<>();

    @Test
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accessCards = getAccessCards();
    }

    @Test
    public void addAccessCard() {
        doNothing().when(accessCardDAO).addAccessCard(any(AccessCard.class));
        accessCardService.addAccessCard(any(AccessCard.class));
        verify(accessCardDAO,atLeastOnce()).addAccessCard(any(AccessCard.class));
    }

    @Test
    public void updateAccessCard() {
        AccessCard accessCard = accessCards.get(0);
        when((accessCardDAO).updateAccessCard(anyObject())).thenReturn(accessCard);
        accessCardService.updateAccessCard(any(AccessCard.class));
        verify(accessCardDAO,atLeastOnce()).updateAccessCard(any(AccessCard.class));
    }

    @Test
    public void deleteAccessCard() {
        doNothing().when(accessCardDAO).deleteAccessCard(anyInt());
        accessCardService.deleteAccessCard(anyInt());
        verify(accessCardDAO,atLeastOnce()).deleteAccessCard(anyInt());
    }

    @Test
    public void getAllAccessCards(){
        when(accessCardDAO.getAllAccessCard()).thenReturn(accessCards);
        Assert.assertEquals(accessCardService.getAllAccessCard(),accessCards);
    }

    @Test
    public void getAccessCard() {
        AccessCard accessCard = accessCards.get(0);
        when(accessCardDAO.getAccessCard(anyInt())).thenReturn(accessCard);
        accessCardService.getAccessCard(anyInt());
        verify(accessCardDAO,atLeastOnce()).getAccessCard(anyInt());
    }

    public List<AccessCard> getAccessCards() {
        List<AccessCard> accessCardList = new ArrayList<>();

        AccessCard accessCard = new AccessCard();
        accessCard.setOrganization("Semanticbits");
        accessCard.setDepartment("software Developer");
        accessCard.setCard_holder_name("Sairaj");

        AccessCard accessCard1 = new AccessCard();
        accessCard1.setOrganization("Semanticbits");
        accessCard1.setDepartment("software Developer");
        accessCard1.setCard_holder_name("Sairaj");

        accessCardList.add(accessCard);
        accessCardList.add(accessCard1);

        return accessCardList;
    }
}

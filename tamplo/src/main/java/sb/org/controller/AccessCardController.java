package sb.org.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sb.org.model.AccessCard;
import sb.org.model.Employee;
import sb.org.service.AccessCardService;
import sb.org.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccessCardController {

    @Autowired
    private AccessCardService accessCardService;

    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = Logger
            .getLogger(EmployeeController.class);

    public AccessCardController() {
        System.out.println("AccessCardController()");
    }

    @RequestMapping(value = "/accessCard")
    public ModelAndView addAccessCard(HttpServletRequest request,ModelAndView model) {
        System.out.println("From Accesscard");
        int accessCardId = Integer.parseInt(request.getParameter("id"));
        AccessCard accessCard = accessCardService.getAccessCard(accessCardId);
        List<AccessCard> accessCards = new ArrayList<>();
        accessCards.add(accessCard);
        System.out.println("Card Holder Name "+accessCard.getCard_holder_name());
        model.addObject("accessCards",accessCards);
        model.setViewName("AccessCardList");
        /*model.setViewName("EmployeeList");*/
        return model;
    }

    @RequestMapping(value = "/allAccessCards")
    public ModelAndView listAccessCards(ModelAndView model) throws IOException {
        List<AccessCard> accessCards = accessCardService.getAllAccessCard();
        model.addObject("accessCards",accessCards);
        model.setViewName("AccessCardList");
        return model;
    }

    @RequestMapping(value = "/editAccessCard", method = RequestMethod.GET)
    public ModelAndView editAccessCard(HttpServletRequest request) {
        int accessCardId = Integer.parseInt(request.getParameter("id"));
        AccessCard accessCard = accessCardService.getAccessCard(accessCardId );
        ModelAndView model = new ModelAndView("AccessCardForm");
        model.addObject("accessCard", accessCard);
        model.addObject("FormName", "Edit");
        return model;
    }

    @RequestMapping(value = "/deleteAccessCard", method = RequestMethod.GET)
    public ModelAndView deleteAccessCard(HttpServletRequest request) {
        int accessCardId = Integer.parseInt(request.getParameter("id"));
        employeeService.deleteEmployee(accessCardId);
        return new ModelAndView("redirect:/allAccessCards");
    }

}

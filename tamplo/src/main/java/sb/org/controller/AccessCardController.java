package sb.org.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sb.org.model.AccessCard;
import sb.org.service.AccessCardService;
import sb.org.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    /*@RequestMapping(value = "/generateAccessCard")
    public ModelAndView addAccessCard(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        employeeService.getEmployee(employeeId);
        return new ModelAndView("redirect:/allEmployees");
    }*/

}

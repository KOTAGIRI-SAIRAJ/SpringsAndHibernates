package sb.org.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sb.org.model.Employee;
import sb.org.model.Task;
import sb.org.service.EmployeeService;
import sb.org.validator.EmployeeValidator;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
     private EmployeeService employeeService;

    @Autowired
    private EmployeeValidator employeeValidator;

    @InitBinder("employee")
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(employeeValidator);
    }

    private static final Logger logger = Logger
            .getLogger(EmployeeController.class);

    public EmployeeController() {
        System.out.println("EmployeeController()");
    }

    @RequestMapping(value = "/")
    public ModelAndView home(ModelAndView model) throws IOException {
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/allEmployees")
    public ModelAndView listEmployee(ModelAndView model,HttpServletRequest request) throws IOException {
        if(request.getParameter("search") == null || request.getParameter("search") == ""){
            List<Employee> listEmployee = employeeService.getAllEmployees();
            model.addObject("listEmployee", listEmployee);
        }else{
            String keyword = request.getParameter("search");
            List<Employee> listEmployee = employeeService.searchForEmployee(keyword);
            model.addObject("listEmployee", listEmployee);
        }
        model.setViewName("EmployeeList");
        return model;
    }

    @RequestMapping(value = "/newEmployee")
    public ModelAndView registerEmployee(ModelAndView model) {
        Employee employee = new Employee();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        employee.setTasks(tasks);
        model.addObject("employee", employee);
        model.addObject("FormName", "Registration Page");
        model.setViewName("EmployeeForm");
        return model;
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute("employee") @Validated Employee employee, BindingResult result, ModelAndView model) {
        model.setViewName("EmployeeForm");
        if(result.hasErrors()){
            return model;
        }
        if (employee.getId() == 0) {
            employeeService.addEmployee(employee);
        } else {
            employeeService.updateEmployee(employee);
        }
        /*ModelAndView model = new ModelAndView();
        model.setViewName("allEmployees");*/
        return new ModelAndView("redirect:/allEmployees");
        /*return model;*/
    }

    @RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
    public ModelAndView editEmployee(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
            Employee employee = employeeService.getEmployee(employeeId);
        /*System.out.println("Task List "+employee.getTasks().size());*/
        ModelAndView model = new ModelAndView("EmployeeForm");
        model.addObject("employee", employee);
        model.addObject("FormName", "Employee Profile");
        return model;
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        /*Employee employee = employeeService.getEmployee(employeeId);
        System.out.println("deleteEmployee called ");
        List<Meeting> meetingList = employee.getMeetings();
        System.out.println(meetingList.size());*/
        employeeService.deleteEmployee(employeeId);
        return new ModelAndView("redirect:/allEmployees");
    }

    /*@RequestMapping(value = "/employee-accessCard", method = RequestMethod.GET)
    public void showAccessCard(HttpServletRequest request,ModelAndView model) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        employeeService.deleteEmployee(employeeId);
        int accessCardId = Integer.parseInt(request.getParameter("id"));
        AccessCard accessCard = accessCardService.getAccessCard(accessCardId);
        List<AccessCard> accessCards = new ArrayList<>();
        accessCards.add(accessCard);
        System.out.println("Card Holder Name "+accessCard.getCard_holder_name());
        model.addObject("accessCards",accessCards);
        model.setViewName("AccessCardList");
    }*/

    /*@RequestMapping(value = "/saveCompleteEmployee", method = RequestMethod.GET)
    public ModelAndView saveCompleteEmployee(HttpServletRequest request) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.getEmployee(employeeId);
        ModelAndView model = new ModelAndView("EmployeeMeetings");
        model.addObject("employee", employee);
        model.addObject("FormName", "Edit");
        return model;
    }*/

    /*@RequestMapping(value = "/allEmployees/Search", method = RequestMethod.GET)
    public ModelAndView searchEmployee(HttpServletRequest request) {
        System.out.println("searchEmployee Controller ");
        String keyword = request.getParameter("search");
        System.out.println("Keyword "+keyword);
        ModelAndView model = new ModelAndView();
        List<Employee> listEmployee = employeeService.searchForEmployee(keyword);
        model.addObject("listEmployee", listEmployee);
        model.setViewName("EmployeeList");
        return model;
    }*/
}

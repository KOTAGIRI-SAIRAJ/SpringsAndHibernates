package sb.org.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import sb.org.model.AccessCard;
import sb.org.model.Employee;
import sb.org.model.Task;
import sb.org.service.EmployeeService;
import sun.awt.ModalExclude;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeTestController {

    @Mock
    EmployeeService employeeService;
    private MockHttpServletRequest request=new MockHttpServletRequest();
    private MockHttpServletResponse response=new MockHttpServletResponse();

    @InjectMocks
    EmployeeController employeeController;

    @Spy
    ModelAndView model;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void home() throws IOException {
        request.setRequestURI("/");
        request.setMethod("GET");
        model.setViewName("home");
        Assert.assertEquals(employeeController.home(model),model);
    }

    @Test
    public void listEmployeeWithEmptySearchKeyword() throws IOException {
        request.setRequestURI("/allEmployees");
        request.setMethod("GET");
        request.setParameter("search","");
        List<Employee> employeeList = employeeList();
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        model.setViewName("EmployeeList");
        Assert.assertEquals(employeeController.listEmployee(model,request),model);
    }

    @Test
    public void listEmployeeWithSearchKeyword() throws IOException {
        request.setRequestURI("/allEmployees");
        request.setMethod("GET");
        request.setParameter("search","sairaj");
        List<Employee> employeeList = employeeList();
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        model.setViewName("EmployeeList");
        Assert.assertEquals(employeeController.listEmployee(model,request),model);
    }

    @Test
    public void registerEmployee() throws IOException{
        request.setRequestURI("/newEmployee");
        request.setMethod("GET");
        Employee employee = employeeRegistration();
        model.setViewName("EmployeeForm");
        Assert.assertEquals(employeeController.registerEmployee(model),model);

    }

    @Test
    public void saveEmployee() {
        request.setRequestURI("/saveEmployee");
        request.setMethod("POST");
        request.setAttribute("employee",employeeRegistration());
        model.setViewName("allEmployees");
        Employee employee = employeeRegistration();
        model = employeeController.saveEmployee(employee);
        Assert.assertEquals(model.getViewName(),"redirect:/allEmployees");
    }

    @Test
    public void editEmployee() {
        String employeeId = "346";
        request.setParameter("id",Integer.toString(346));
        Employee employee = employeeRegistration();
        request.setAttribute("employee",employeeRegistration());
        model = employeeController.editEmployee(request);
        Assert.assertEquals(model.getViewName(),"EmployeeForm");
            verify(employeeService,times(1)).getEmployee((anyInt()));
    }

    @Test
    public void deleteEmployee() {
        request.setParameter("id",Integer.toString(346));
        model = employeeController.deleteEmployee(request);
        Assert.assertEquals(model.getViewName(),"redirect:/allEmployees");
            verify(employeeService,times(1)).deleteEmployee((anyInt()));
    }


    public List<Employee> employeeList() {

        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setName("sairaj");
        employee.setEmail("sairaj@gmail.com");
        employee.setDepartment("Software Developer");
        employee.setSalary(60000);
        employee.setTelephone("8142428303");
        employee.setId(1);
        employeeList.add(employee);

        Employee employeeTwo = new Employee();
        employeeTwo.setName("puram");
        employeeTwo.setEmail("puram@gmail.com");
        employeeTwo.setDepartment("Software Developer");
        employeeTwo.setSalary(70000);
        employeeTwo.setTelephone("8142428304");
        employeeTwo.setId(2);
        employeeList.add(employeeTwo);

        return employeeList;
    }

    public Employee employeeRegistration() {

        Employee employee = new Employee();
        employee.setName("sairaj");
        employee.setEmail("sairaj@gmail.com");
        employee.setDepartment("Software Developer");
        employee.setSalary(60000);
        employee.setTelephone("8142428303");

        AccessCard accessCard = new AccessCard();
        accessCard.setCard_holder_name("sairaj");
        accessCard.setDepartment("Software Developer");
        accessCard.setOrganization("Semanticbits");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        employee.setTasks(tasks);
        employee.setAccessCard(accessCard);

        return employee;
    }

}



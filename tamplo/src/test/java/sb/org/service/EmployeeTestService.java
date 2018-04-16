package sb.org.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import sb.org.dao.EmployeeDAO;
import sb.org.model.Employee;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class EmployeeTestService {

    @Mock
    EmployeeDAO employeeDAO;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Spy
    List<Employee> employeeList = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeList = getEmployeeList();
    }

    @Test
    public void addEmployee() {
        doNothing().when(employeeDAO).addEmployee(any(Employee.class));
        employeeService.addEmployee(any(Employee.class));
        verify(employeeDAO,atLeastOnce()).addEmployee(any(Employee.class));
    }

    @Test
    public void deleteEmployee() {
        doNothing().when(employeeDAO).deleteEmployee(anyInt());
        employeeService.deleteEmployee(anyInt());
        verify(employeeDAO,atLeastOnce()).deleteEmployee(anyInt());
    }

    @Test
    public void getEmployee() {
        Employee employee = employeeList.get(0);
        when(employeeDAO.getEmployee(anyInt())).thenReturn(employee);
        /*doNothing().when(employeeDAO).getEmployee(anyInt());*/
        employeeService.getEmployee(anyInt());
        verify(employeeDAO,atLeastOnce()).getEmployee(anyInt());
    }

    @Test
    public void updateEmployee(){
        Employee employee = employeeList.get(0);
        when(employeeDAO.updateEmployee(anyObject())).thenReturn(employee);
        employeeService.updateEmployee(anyObject());
        verify(employeeDAO,atLeastOnce()).updateEmployee(anyObject());
    }

    @Test
    public void getAllEmployees(){
        when(employeeDAO.getAllEmployees()).thenReturn(employeeList);
        Assert.assertEquals(employeeService.getAllEmployees(),employeeList);
    }

    @Test
    public void searchForEmployee(){
        when(employeeDAO.searchForEmployee(anyString())).thenReturn(employeeList);
        employeeService.searchForEmployee(anyString());
        verify(employeeDAO,atLeastOnce()).searchForEmployee(anyString());
    }


    @Test
    public void getUnEnrolledEmployees() {
        when(employeeDAO.getUnEnrolledEmployees(any())).thenReturn(employeeList);
        employeeService.getUnEnrolledEmployees(any());
        verify(employeeDAO,atLeastOnce()).getUnEnrolledEmployees(any());
    }

    public List<Employee> getEmployeeList() {
        Employee employee = new Employee();
        Employee employee1 = new Employee();

        employee.setId(1);
        employee.setDepartment("Software");
        employee.setSalary(1000);
        employee.setTelephone("5461232789");
        employee.setEmail("something@gmail.com");
        employee.setName("something");

        employee1.setId(1);
        employee1.setDepartment("Software");
        employee1.setSalary(1000);
        employee1.setTelephone("5461232789");
        employee1.setEmail("something@gmail.com");
        employee1.setName("something");

        employeeList.add(employee);
        employeeList.add(employee1);
        return employeeList;
    }
}

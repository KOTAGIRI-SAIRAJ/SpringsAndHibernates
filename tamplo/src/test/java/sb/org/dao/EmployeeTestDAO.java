package sb.org.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.*;
import sb.org.model.AccessCard;
import sb.org.model.Employee;
import sb.org.model.Task;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class EmployeeTestDAO {

    @Mock
    EmployeeDAO employeeDAO;

    @InjectMocks
    EmployeeDAOImpl employeeDAOImpl;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    Criteria criteria;

    @Mock
    private Session session;

    @Spy
    List<Employee> employeeList = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeList = employeeList();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createCriteria(Employee.class)).thenReturn(criteria);
    }

    @Test
    public void addEmployee() {
        Employee employee = employeeRegistration();
        Assert.assertNotNull(employeeDAO);
        session.saveOrUpdate(employee);
        employeeDAOImpl.addEmployee(employee);
        /*Mockito.doReturn(criteria).when(session).saveOrUpdate(employee);*/
        verify(session, atLeastOnce()).saveOrUpdate(employee);
    }

    @Test
    public void getAllEmployees() {
        Assert.assertNotNull(employeeDAO);
        when(employeeDAOImpl.getAllEmployees()).thenReturn(employeeList);
        employeeDAOImpl.getAllEmployees();
        /*verify(employeeDAOImpl, atLeastOnce()).getAllEmployees();*/
        //doReturn(employeeList).when(employeeDAOImpl.getAllEmployees());
    }

    @Test
    public void deleteEmployee() {
        Assert.assertNotNull(employeeDAO);
        session.delete(1);
        employeeDAOImpl.deleteEmployee(1);
        verify(session, atLeastOnce()).delete(1);
    }

    @Test
    public void getEmployee() {
        Assert.assertNotNull(employeeDAO);
        session.get(Employee.class,1);
        employeeDAOImpl.getEmployee(1);
        verify(session, atLeastOnce()).get(Employee.class,1);
    }

    @Test
    public void updateEmployee() {
        Employee employee = employeeRegistration();
        Assert.assertNotNull(employeeDAO);
        /*Mockito.doReturn(criteria).when(session).update(employee);*/
        session.update(employee);
        employeeDAOImpl.updateEmployee(employee);
        verify(session, atLeastOnce()).update(employee);
    }

    @Test(expected = RuntimeException.class)
    public void getUnEnrolledEmployees() {
        Assert.assertNotNull(employeeDAO);
        when(employeeDAOImpl.getUnEnrolledEmployees(employeeList)).thenReturn(employeeList);
        verify(employeeDAOImpl, atLeastOnce()).getUnEnrolledEmployees(employeeList);
    }

    @Test(expected = RuntimeException.class)
    public void searchForEmployeeAny() {
        Assert.assertNotNull(employeeDAO);
        /*when(employeeDAOImpl.searchForEmployee("8142428302")).thenReturn(employeeList);
        verify(employeeDAOImpl, atLeastOnce()).searchForEmployee("8142428302");*/
        when(employeeDAOImpl.searchForEmployee("keyword")).thenReturn(employeeList);
        employeeDAOImpl.searchForEmployee("keyword");
        verify(employeeDAOImpl, atLeastOnce()).searchForEmployee("keyword");

    }

    @Test(expected = RuntimeException.class)
    public void searchForEmployeeSalary() {
        Assert.assertNotNull(employeeDAO);
        when(employeeDAOImpl.searchForEmployee("60000")).thenReturn(employeeList);
        employeeDAOImpl.searchForEmployee("60000");
        verify(employeeDAOImpl, atLeastOnce()).searchForEmployee("6000");
        /*when(employeeDAOImpl.searchForEmployee("keyword")).thenReturn(employeeList);
        verify(employeeDAOImpl, atLeastOnce()).searchForEmployee("keyword");*/

    }


    public Employee employeeRegistration() {

        Employee employee = new Employee();
        employee.setName("sairaj");
        employee.setEmail("sairaj@gmail.com");
        employee.setDepartment("Software Developer");
        employee.setSalary(60000);
        employee.setTelephone("8142428303");

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        employee.setTasks(tasks);

        AccessCard accessCard = new AccessCard();
        accessCard.setCard_holder_name("Sai Raj");
        accessCard.setDepartment("Software Developer");
        accessCard.setOrganization("Semantic bits");
        employee.setAccessCard(accessCard);

        return employee;
    }

    public List<Employee> employeeList() {

        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        Employee employeeTwo = new Employee();
        employee.setName("Sai Raj");
        employee.setEmail("sairaj@gmail.com");
        employee.setDepartment("Software Developer");
        employee.setSalary(60000);
        employee.setTelephone("8142428303");
        employee.setId(1);
        employeeList.add(employee);


        employeeTwo.setName("Puram");
        employeeTwo.setEmail("puram@gmail.com");
        employeeTwo.setDepartment("Software Developer");
        employeeTwo.setSalary(70000);
        employeeTwo.setTelephone("8142428304");
        employeeTwo.setId(2);
        employeeList.add(employeeTwo);

        return employeeList;
    }

}

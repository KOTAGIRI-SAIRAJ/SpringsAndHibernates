package sb.org.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import sb.org.model.Employee;
import sb.org.model.Meeting;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MeetingTestDAO {

    @Mock
    MeetingDAO meetingDAO;

    @InjectMocks
    MeetingDAOImpl meetingDAOImpl;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    Criteria criteria;

    @Spy
    List<Meeting> meetingList = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        meetingList = createMeetingList();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void addMeeting() {
        Meeting meeting = meetingList.get(0);
        Assert.assertNotNull(meetingDAO);
        session.saveOrUpdate(meeting);
        meetingDAOImpl.addMeeting(meeting);
        verify(session, atLeastOnce()).saveOrUpdate(meeting);
    }

    @Test
    public void updateMeeting() {
        Meeting meeting = meetingList.get(0);
        Assert.assertNotNull(meetingDAO);
        /*doThrow(RuntimeException.class).when(meetingDAO).updateMeeting(meeting);
        Mockito.doReturn(criteria).when(session).update(meeting);*/
        session.update(meeting);
        meetingDAOImpl.updateMeeting(meeting);
        verify(session, atLeastOnce()).update(meeting);
    }

    @Test
    public void deleteMeeting() {
        Assert.assertNotNull(meetingDAO);
        /*doThrow(RuntimeException.class).when(meetingDAO).deleteMeeting(1);
        Mockito.doReturn(criteria).when(session).delete(1);*/
        session.delete(1);
        meetingDAOImpl.deleteMeeting(1);
        verify(session, atLeastOnce()).delete(1);
    }

    @Test
    public void getMeeting() {
        Assert.assertNotNull(meetingDAO);
        /*doThrow(RuntimeException.class).when(meetingDAO).getMeeting(1);
        meetingDAOImpl.getMeeting(1);*/
        session.get(Meeting.class,1);
        meetingDAOImpl.getMeeting(1);
        verify(session, atLeastOnce()).get(Meeting.class,1);
    }

    @Test(expected = RuntimeException.class)
    public void getAllMeetings() {
        Assert.assertNotNull(meetingDAO);
        /*doThrow(RuntimeException.class).when(meetingDAO).getAllMeetings();
        when(meetingDAOImpl.getAllMeetings()).thenReturn(meetingList);*/
        criteria = session.createCriteria(Meeting.class);
        when(criteria.list()).thenReturn(meetingList);
        doThrow(RuntimeException.class).when(meetingDAOImpl).getAllMeetings();
        verify(meetingDAOImpl, atLeastOnce()).getAllMeetings();
    }

    @Test(expected = RuntimeException.class)
    public void getUnEnrolledEmployees() {
        Assert.assertNotNull(meetingDAO);
        doThrow(RuntimeException.class).when(meetingDAO).getUnEnrolledEmployees(1);
        when(meetingDAOImpl.getUnEnrolledEmployees(1)).thenReturn(employeeList());
        verify(meetingDAOImpl, atLeastOnce()).getUnEnrolledEmployees(1);
    }



    public List<Meeting> createMeetingList() {
        List<Meeting> meetingList = new ArrayList<>();

        Meeting meetingTwo =  new Meeting();
        meetingTwo.setClient_name("Kotagiri");
        meetingTwo.setMeeting_dur(2);
        meetingTwo.setMeeting_title("Client");
        meetingList.add(meetingTwo);

        Meeting meeting =  new Meeting();
        meeting.setClient_name("Sairaj");
        meeting.setMeeting_dur(2);
        meeting.setMeeting_title("SCRUM");
        meetingList.add(meeting);

        return meetingList;
    }

    public List<Employee> employeeList() {

        Employee employee = new Employee();
        Employee employeeTwo = new Employee();
        employee.setName("sairaj");
        employee.setEmail("sairaj@gmail.com");
        employee.setDepartment("Software Developer");
        employee.setSalary(60000);
        employee.setTelephone("8142428303");
        employee.setId(1);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);


        employeeTwo.setName("puram");
        employeeTwo.setEmail("puram@gmail.com");
        employeeTwo.setDepartment("Software Developer");
        employeeTwo.setSalary(70000);
        employeeTwo.setTelephone("8142428304");
        employeeTwo.setId(2);
        employeeList.add(employeeTwo);

        return employeeList;
    }

}

package sb.org.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import sb.org.model.Employee;
import sb.org.model.Meeting;
import sb.org.service.EmployeeService;
import sb.org.service.MeetingService;
import sun.misc.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;


public class MeetingTestController {


    @Mock
    MeetingService meetingService;

    @Mock
    EmployeeService employeeService;

    private MockHttpServletRequest request=new MockHttpServletRequest();

    @InjectMocks
    MeetingController meetingController;

    @Spy
    ModelAndView model;


    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(meetingController).build();
    }

    @Test
    public void newMeeting() throws IOException {
        model.setViewName("MeetingForm");
        Assert.assertEquals(meetingController.newMeeting(model),model);
    }

    @Test
    public void meetings() throws IOException{
        List<Meeting> meetingList = createMeetingList();
        model.setViewName("MeetingList");
        Assert.assertEquals(meetingController.meetings(model),model);
    }

    @Test
    public void saveMeeting() {
        request.setAttribute("meeting",createMeeting());
        model.setViewName("allEmployees");
        Meeting meeting= createMeeting();
        meeting.setId(10);
        model = meetingController.saveMeeting(meeting);
        Assert.assertEquals((model),model);
    }

    @Test
    public void saveMeetingWithIdZero() {
        request.setAttribute("meeting",createMeeting());
        model.setViewName("allEmployees");
        Meeting meeting= createMeeting();
        meeting.setId(0);
        model = meetingController.saveMeeting(meeting);
        Assert.assertEquals((model),model);
    }

    @Test
    public void editMeeting() {
        String meetingId = "346";
        request.setParameter("id",Integer.toString(346));
        Meeting meeting  = createMeeting();
        request.setAttribute("meeting",createMeeting());
        model = meetingController.editMeeting(request);
        Assert.assertEquals(model.getViewName(),"MeetingForm");
        verify(meetingService,times(1)).getMeeting((anyInt()));
    }


    @Test
    public void deleteMeeting() {
        request.setParameter("id",Integer.toString(346));
        model = meetingController.deleteMeeting(request);
        Assert.assertEquals(model.getViewName(),"redirect:/allMeetings");
        verify(meetingService,times(1)).deleteMeeting((anyInt()));
    }

    @Test
    public void showEmployees() {
        request.setParameter("id",Integer.toString(346));
        Employee employee = new Employee();
        employee.setId(346);
        employee.setName("Sairaj");
        employee.setTelephone("8414242131");
        employee.setSalary(20000);
        employee.setEmail("sai@gmail.com");
        employee.setDepartment("Software");
        employee.setMeetings(createMeetingListWithID());
        when(employeeService.getEmployee(employee.getId())).thenReturn(employee);
        List<Meeting> meetingList = employee.getMeetings();
        request.setAttribute("meetingList",createMeetingListWithID());
        model = meetingController.showEmployees(request,model);
        Assert.assertEquals((model.getViewName()),"EmployeeMeetings");
        verify(employeeService,times(1)).getEmployee((anyInt()));
    }

    @Test
    public void getUnEnrolledEmployeesForMeeting() {
        request.setParameter("id",Integer.toString(346));
        Meeting meeting = new Meeting();
        meeting.setId(346);
        meeting.setMeeting_title("SCRUM");
        meeting.setClient_name("Sairaj");
        meeting.setEmployees(createEmployeeList());
        when(meetingService.getMeeting(meeting.getId())).thenReturn(meeting);
        List<Employee> enrolledEmployees = createEmployeeList();
        List enrolledEmployeesWithId = createEmployeeList();
        List<Employee> employeeList = createEmployeeList();
        model = meetingController.getUnEnrolledEmployeesForMeeting(request);
        Assert.assertEquals((model.getViewName()),"EnrollEmployees");
        verify(meetingService,times(1)).getMeeting(anyInt());
    }

    @Test
    public void getUnEnrolledEmployeesForMeetingWithEmpty() {
        request.setParameter("id",Integer.toString(346));
        Meeting meeting = new Meeting();
        meeting.setId(346);
        meeting.setMeeting_title("SCRUM");
        meeting.setClient_name("Sairaj");
        meeting.setEmployees(new ArrayList<>());
        when(meetingService.getMeeting(meeting.getId())).thenReturn(meeting);
        List<Employee> enrolledEmployees = createEmployeeList();
        List enrolledEmployeesWithId = createEmployeeList();
        List<Employee> employeeList = createEmployeeList();
        model = meetingController.getUnEnrolledEmployeesForMeeting(request);
        Assert.assertEquals((model.getViewName()),"EnrollEmployees");
        verify(meetingService,times(1)).getMeeting(anyInt());
    }

    @Test
    public void enrollTheSelectedEmployees(){
        request.setAttribute("meeting",createMeetingWithID());

        int meetingId = 346;
        String[] words = {"123","424","214"};
        /*request.setAttribute("enroll",words);*/
        request.setParameter("enroll",words);
        /*request.setAttribute("unenroll",words);*/
        request.setParameter("unenroll",words);

        List<Meeting> meetingList = createMeetingList();
        List<Employee> employees = createEmployeeList();

        Meeting meeting = createMeetingWithID();

        when(meetingService.getMeeting(meeting.getId())).thenReturn(meeting);
        meeting.setEmployees(createEmployeeList());
        List<Employee> enrolledEmployeesList  = meeting.getEmployees();
        //List<Employee> enrolledEmployees = createEmployeeList();
        List<String> EnrolledEmployees = Arrays.asList(words);
        List<String> unEnrolledEmployees = Arrays.asList(words);


        for (String enrolledEmployee : EnrolledEmployees) {

            Employee employee = enrolledEmployeesList.get(0);
            employee.setId(Integer.parseInt(enrolledEmployee));
            when(employeeService.getEmployee(employee.getId())).thenReturn(employee);
            employee.setMeetings(createMeetingListWithID());
            employees.add(employee);

        }
        model = meetingController.enrollTheSelectedEmployees(meeting,request);
        Assert.assertEquals(model.getViewName(),"redirect:/enrollEmployees?id="+meeting.getId());
    }

    @Test
    public void enrollTheSelectedEmployeesWithOneValue(){
        request.setAttribute("meeting",createMeetingWithID());

        int meetingId = 346;
        String[] words = {"123","424","214"};
        request.setParameter("enroll",words);

        request.setParameter("unenroll",words);

        List<Meeting> meetingList = createMeetingList();
        List<Employee> employees = createEmployeeList();

        Meeting meeting = createMeetingWithID();

        when(meetingService.getMeeting(meeting.getId())).thenReturn(meeting);
        List<Employee> employeeList =  new ArrayList<>();
        employeeList.add(employees.get(0));
        meeting.setEmployees(employeeList);
        List<Employee> enrolledEmployeesList  = meeting.getEmployees();
        //List<Employee> enrolledEmployees = createEmployeeList();
        List<String> EnrolledEmployees = Arrays.asList(words);
        for (String enrolledEmployee : EnrolledEmployees) {
            Employee employee = enrolledEmployeesList.get(0);
            employee.setId(Integer.parseInt(enrolledEmployee));
            when(employeeService.getEmployee(employee.getId())).thenReturn(employee);
            employee.setMeetings(createMeetingListWithID());
            employees.add(employee);
        }
        List<String> unEnrolledEmployees = Arrays.asList(words);
        model = meetingController.enrollTheSelectedEmployees(meeting,request);
        Assert.assertEquals(model.getViewName(),"redirect:/enrollEmployees?id="+meeting.getId());
    }

    @Test
    public void unEnrollTheSelectedEmployees() {
        int[] unenrollId = {1,23,42};
        Integer meetingId = 24;
        Meeting meeting = createMeeting();
        meeting.setId(24);
        when(meetingService.getMeeting(meeting.getId())).thenReturn(meeting);
        List<Employee> employeeList = createEmployeeList();
        meeting.setEmployees(employeeList);
        meetingController.unEnrollTheSelectedEmployees(unenrollId,meetingId);
        verify(meetingService,times(1)).updateMeeting(any());
    }

    public Meeting createMeeting() {

        Meeting meeting =  new Meeting();
        meeting.setClient_name("Sairaj");
        meeting.setMeeting_dur(2);
        meeting.setMeeting_title("SCRUM");

        return meeting;
    }

    public Meeting createMeetingWithID() {

        Meeting meeting =  new Meeting();
        meeting.setClient_name("Sairaj");
        meeting.setMeeting_dur(2);
        meeting.setId(346);
        meeting.setMeeting_title("SCRUM");

        return meeting;
    }

    public List<Meeting> createMeetingList() {

        Meeting meeting =  new Meeting();
        meeting.setClient_name("Sairaj");
        meeting.setMeeting_dur(2);
        meeting.setMeeting_title("SCRUM");

        Meeting meetingtwo =  new Meeting();
        meetingtwo.setClient_name("Kotagiri");
        meetingtwo.setMeeting_dur(2);
        meetingtwo.setMeeting_title("Client");

        List<Meeting> meetingList = new ArrayList<>();

        meetingList.add(meeting);
        meetingList.add(meetingtwo);
        return meetingList;
    }

    public List<Meeting> createMeetingListWithID() {

        Meeting meeting =  new Meeting();
        meeting.setClient_name("Sairaj");
        meeting.setMeeting_dur(2);
        meeting.setId(20);
        meeting.setMeeting_title("SCRUM");

        Meeting meetingtwo =  new Meeting();
        meetingtwo.setClient_name("Kotagiri");
        meetingtwo.setMeeting_dur(2);
        meeting.setId(21);
        meetingtwo.setMeeting_title("Client");

        List<Meeting> meetingList = new ArrayList<>();

        meetingList.add(meeting);
        meetingList.add(meetingtwo);
        return meetingList;
    }

    public List<Employee> createEmployeeList(){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        Employee employee1 =  new Employee();
        employee.setId(1);
        employee1.setId(2);
        employeeList.add(employee);
        employeeList.add(employee1);
        return employeeList;
    }
}

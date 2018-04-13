package sb.org.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import sb.org.model.Employee;
import sb.org.model.Meeting;
import sb.org.service.EmployeeService;
import sb.org.service.MeetingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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

    /*@Test
    public void showEmployees() {
        request.setParameter("id",Integer.toString(346));
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Sairaj");
        employee.setTelephone("8414242131");
        employee.setSalary(20000);
        employee.setEmail("sai@gmail.com");
        employee.setDepartment("Software");
        employee.setMeetings(createMeetingListWithID());
        List<Meeting> meetingList = employee.getMeetings();
        when(employeeService.getEmployee(employee.getId())).thenReturn(employee);

        *//*when(meetingController.showEmployees(request,model)).thenReturn(model);*//*
        request.setAttribute("meetingList",createMeetingListWithID());

        *//*when(meetingController.showEmployees(request,model)).thenReturn(model);*//*
        model = meetingController.showEmployees(request,model);
        Assert.assertEquals((model.getViewName()),"EmployeeMeetings");
        verify(employeeService,times(1)).getEmployee((anyInt()));
    }*/

    public Meeting createMeeting() {

        Meeting meeting =  new Meeting();
        meeting.setClient_name("Sairaj");
        meeting.setMeeting_dur(2);
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
}

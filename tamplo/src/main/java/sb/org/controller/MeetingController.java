package sb.org.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;
import sb.org.model.Employee;
import sb.org.model.Meeting;
import sb.org.service.EmployeeService;
import sb.org.service.MeetingService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = Logger
            .getLogger(MeetingController.class);

    public MeetingController() {
        System.out.println("MeetingController()");
    }


    @RequestMapping(value = "/newMeeting")
    public ModelAndView newMeeting(ModelAndView model) throws IOException {
        Meeting meeting= new Meeting();
        model.addObject("meeting" ,meeting);
        model.addObject("FormName", "New");
        model.setViewName("MeetingForm");
        return model;
    }
    @RequestMapping(value = "/allMeetings")
    public ModelAndView meetings(ModelAndView modal){
        List<Meeting> meetingList = meetingService.getAllMeetings();
        modal.addObject("meetingList",meetingList);
        modal.setViewName("MeetingList");
        return modal;
    }

    @RequestMapping(value = "/saveMeeting", method = RequestMethod.POST)
    public ModelAndView saveMeeting(@ModelAttribute Meeting meeting) {
        if (meeting.getId() == 0) {
            meetingService.addMeeting(meeting);
        } else {
            meetingService.updateMeeting(meeting);
        }
        return new ModelAndView("redirect:/allMeetings");
    }

    @RequestMapping(value = "/editMeeting", method = RequestMethod.GET)
    public ModelAndView editMeeting(HttpServletRequest request) {
        int meetingId = Integer.parseInt(request.getParameter("id"));
        Meeting meeting = meetingService.getMeeting(meetingId);
        List<Employee> employees= employeeService.getAllEmployees();
        ModelAndView model = new ModelAndView("MeetingForm");
        model.addObject("meeting", meeting);
        model.addObject("employees",employees);
        model.addObject("FormName", "Edit");
        return model;
    }

    @RequestMapping(value = "/deleteMeeting", method = RequestMethod.GET)
    public ModelAndView deleteMeeting(HttpServletRequest request) {
        int meetingId = Integer.parseInt(request.getParameter("id"));
        meetingService.deleteTask(meetingId);
        return new ModelAndView("redirect:/allMeetings");
    }

    @RequestMapping(value = "/meetings", method = RequestMethod.GET)
    public ModelAndView showMeeting(HttpServletRequest request, ModelAndView modal) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.getEmployee(employeeId);
        List<Meeting> meetingList = employee.getMeetings();
        modal.addObject("EmployeeMeetings",meetingList);
        modal.setViewName("EmployeeMeetings");
        return modal;
    }
}

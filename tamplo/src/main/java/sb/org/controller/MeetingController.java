package sb.org.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;
import sb.org.model.Employee;
import sb.org.model.Meeting;
import sb.org.model.Task;
import sb.org.service.EmployeeService;
import sb.org.service.MeetingService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

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
    public ModelAndView showEmployees(HttpServletRequest request, ModelAndView modal) {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.getEmployee(employeeId);
        List<Meeting> meetingList = employee.getMeetings();
        modal.addObject("EmployeeMeetings",meetingList);
        modal.setViewName("EmployeeMeetings");
        return modal;
    }

    @RequestMapping(value = "/enrollEmployees",method = RequestMethod.GET)
    private ModelAndView getUnEnrolledEmployeesForMeeting(HttpServletRequest request){
        int meetingId = Integer.parseInt(request.getParameter("id"));
        Meeting meeting = meetingService.getMeeting(meetingId);
        List<Employee> enrolledEmployees = meeting.getEmployees();
        List enrolledEmployeesWithId = new ArrayList<>();
        List<Employee> employeeList;
        for (Employee enrolledEmployee : enrolledEmployees) {
            enrolledEmployeesWithId.add(enrolledEmployee.getId());
        }
        if(enrolledEmployeesWithId.size() > 0){
            employeeList = employeeService.getUnEnrolledEmployees(enrolledEmployeesWithId);
        }else{
            employeeList = employeeService.getAllEmployees();
        }
        /*List<Employee> employeeList = meetingService.getUnEnrolledEmployeesDetails(meetingId);*/
        ModelAndView modal = new ModelAndView("EnrollEmployees");
        modal.addObject("meeting",meeting);
        modal.addObject("employees",enrolledEmployees);
        modal.addObject(employeeList);
        modal.setViewName("EnrollEmployees");
        return modal;
    }

    @RequestMapping(value = "/enrollSelectedEmployees",method = RequestMethod.POST)
    private ModelAndView enrollTheSelectedEmployees(@ModelAttribute Meeting meetingObj,HttpServletRequest request){
        int meetingId = meetingObj.getId();
        List<String> EnrolledEmployees = new ArrayList<>();
        List<String> unEnrolledEmployees = new ArrayList<>();

        if(request.getParameterValues("enroll") == null && request.getParameterValues("unenroll") == null) {
            return new ModelAndView("redirect:/enrollEmployees?id="+meetingId);
        }
        if(request.getParameterValues("enroll") != null) {
            EnrolledEmployees = Arrays.asList(request.getParameterValues("enroll"));
        }
        if(request.getParameterValues("unenroll") != null) {
            unEnrolledEmployees = Arrays.asList(request.getParameterValues("unenroll"));
        }
        List<Meeting> meetingList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        Meeting meeting = meetingService.getMeeting(meetingId);
        List<Employee> enrolledEmployeesList  = meeting.getEmployees();
        meetingList.add(meeting);

        if(unEnrolledEmployees.size() >= 0){
            for (String eachEnrollEmpId : unEnrolledEmployees) {
                int counter = 0;
                for (Employee employee : enrolledEmployeesList) {
                    if(employee.getId() == Integer.parseInt(eachEnrollEmpId)){
                        if(enrolledEmployeesList.size() == 1){
                            enrolledEmployeesList.clear();
                            counter = -1;
                            break;
                        }else{
                            enrolledEmployeesList.remove(counter);
                        }
                    }


                    counter++;
                }
                if(counter == -1){
                    break;
                }
            }
        }
        for (String enrolledEmployee : EnrolledEmployees) {
            Employee employee = employeeService.getEmployee(Integer.parseInt(enrolledEmployee));
            employee.setMeetings(meetingList);
            employees.add(employee);
        }
        List<Employee> enrolledEmployees = enrolledEmployeesList;
        if(employees.size() > 0){
            enrolledEmployees.addAll(employees);
        }
        meeting.setEmployees(enrolledEmployees);
        meetingService.updateMeeting(meeting);
        return new ModelAndView("redirect:/enrollEmployees?id="+meetingId);
    }

    @RequestMapping(value = "/unEnrollSelectedEmployees",method = RequestMethod.GET)
    public @ResponseBody String unEnrollTheSelectedEmployees(@RequestParam(value="unenrollId[]") int[] unenrollId, @RequestParam Integer meetingId){
        Meeting meeting = meetingService.getMeeting(meetingId);
        List<Employee> employeeList = meeting.getEmployees();

        for (int i = 0; i < unenrollId.length; i++) {
            for (int j = 0; j < employeeList.size(); j++) {
                Employee employee = employeeList.get(j);
                if(unenrollId[i] == employee.getId()){
                    employeeList.remove(employeeList.indexOf(employee));
                    System.out.println("Removed ");
                }
            }
        }
        meeting.setEmployees(employeeList);
        meetingService.updateMeeting(meeting);
        return "/enrollEmployees";
    }
}

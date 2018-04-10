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
import sb.org.model.Task;
import sb.org.service.EmployeeService;
import sb.org.service.MeetingService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
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
        System.out.println("Meeting Id "+meetingId);
        if(request.getParameterValues("enroll") == null) {
            return new ModelAndView("redirect:/enrollEmployees?id="+meetingId);
        }
        String checkboxValues[] = request.getParameterValues("enroll");
        System.out.println("checkboxValues length "+checkboxValues.length);
        List<Meeting> meetingList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        Meeting meeting = meetingService.getMeeting(meetingId);
        meetingList.add(meeting);
        for (String checkboxValue : checkboxValues) {
            Employee employee = employeeService.getEmployee(Integer.parseInt(checkboxValue));
            employee.setMeetings(meetingList);
            employees.add(employee);
        }
        List<Employee> enrolledEmployees = meeting.getEmployees();
        enrolledEmployees.addAll(employees);
        meeting.setEmployees(enrolledEmployees);
        meetingService.updateMeeting(meeting);
        return new ModelAndView("redirect:/enrollEmployees?id="+meetingId);
    }

    /*@RequestMapping(value = "/unEnrollSelectedEmployees",method = RequestMethod.GET)
    private void unEnrollTheSelectedEmployees(HttpServletRequest request){

        int meetingId = Integer.parseInt(request.getParameter("id"));
        *//*if(request.getParameterValues("unenroll") == null) {
            return new ModelAndView("redirect:/enrollEmployees?id="+meetingId);
        }*//*
        String checkboxValues[] = request.getParameterValues("unenroll");
        Meeting meeting = meetingService.getMeeting(meetingId);
        List<Meeting> meetingList = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        List<Employee> enrolledEmployees = meeting.getEmployees();
        List<Employee> removedSelectedEnrolledEmployees;

        *//*enrolledEmployees = enrolledEmployees.filter(function( obj ) {
            return obj.id !== 337;
        });*//*
        System.out.println("CheckBox Values length "+checkboxValues.length);
        System.out.println("Enrolled Employees before delete "+enrolledEmployees.size());
        for (Employee enrolledEmployee : enrolledEmployees) {
            for (String checkboxValue : checkboxValues) {
                Employee employee = employeeService.getEmployee(Integer.parseInt(checkboxValue));
                if(employee.getId() == enrolledEmployee.getId()){
                    System.out.println("Matched Employee ID "+employee.getId());
                    enrolledEmployees.remove(enrolledEmployee);
                }
            }
        }
        System.out.println("Enrolled Employees after delete "+enrolledEmployees.size());

        *//*Meeting meeting = meetingService.getMeeting(meetingId);
        meetingList.add(meeting);
        for (String checkboxValue : checkboxValues) {
            Employee employee = employeeService.getEmployee(Integer.parseInt(checkboxValue));
            employee.setMeetings(meetingList);
            employees.add(employee);
        }
        List<Employee> enrolledEmployees = meeting.getEmployees();
        enrolledEmployees.addAll(employees);
        meeting.setEmployees(enrolledEmployees);
        meetingService.updateMeeting(meeting);*//*
        *//*return new ModelAndView("redirect:/enrollEmployees?id="+meetingId);*//*
    }*/
}

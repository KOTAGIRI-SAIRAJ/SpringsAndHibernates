package sb.org.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sb.org.model.AccessCard;
import sb.org.model.Employee;
import sb.org.model.Task;
import sb.org.service.EmployeeService;
import sb.org.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = Logger
            .getLogger(TaskController.class);

    public TaskController() {
        System.out.println("TaskController()");
    }

    @RequestMapping(value = "/tasks")
    public ModelAndView tasks(ModelAndView model) throws IOException {
        model.setViewName("MeetingList");
        return model;
    }

    @RequestMapping(value = "/createTask")
    public ModelAndView newTask(ModelAndView model) throws IOException {
        Task task = new Task();
        List<Employee> employees= employeeService.getAllEmployees();
        model.addObject("employees",employees);
        model.addObject("task", task);
        model.addObject("FormName", "New");
        model.setViewName("CreateTask");
        return model;
    }

    @RequestMapping(value = "/allTasks")
    public ModelAndView listTasks(ModelAndView model,HttpServletRequest request) throws IOException {
        List<Task> taskList = taskService.getAllTasks();
        model.addObject("taskList", taskList);
        model.setViewName("TaskList");
        return model;
    }

    @RequestMapping(value = "/saveTask", method = RequestMethod.POST)
    public ModelAndView saveTask(@ModelAttribute Task task) {
        if (task.getId() == 0) {
            taskService.addTask(task);
        } else {
            taskService.updateTask(task);
        }
        return new ModelAndView("redirect:/allTasks");
    }

    @RequestMapping(value = "/editTask", method = RequestMethod.GET)
    public ModelAndView editTask(HttpServletRequest request) {
        int taskId = Integer.parseInt(request.getParameter("id"));
        Task task = taskService.getTask(taskId);
        List<Employee> employees= employeeService.getAllEmployees();
        ModelAndView model = new ModelAndView("CreateTask");
        model.addObject("task", task);
        model.addObject("employees",employees);
        model.addObject("FormName", "Edit");
        return model;
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.GET)
    public ModelAndView deleteTask(HttpServletRequest request) {
        int taskId = Integer.parseInt(request.getParameter("id"));
        /*Task task = taskService.getTask(taskId);
        task.setEmployee(null);
        taskService.updateTask(task);*/
        taskService.deleteTask(taskId);
        return new ModelAndView("redirect:/allTasks");
    }

    @RequestMapping(value = "/tasksById")
    public ModelAndView listTasksById(ModelAndView model,HttpServletRequest request) throws IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.getEmployee(employeeId);
        List<Task> taskList = employee.getTasks();
        model.addObject("taskList", taskList);
        model.setViewName("TaskList");
        return model;
    }
}

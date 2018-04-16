package sb.org.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import sb.org.dao.TaskDAO;
import sb.org.model.Task;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class TaskTestService {

    @Mock
    TaskDAO taskDAO;

    @InjectMocks
    TaskServiceImpl taskService;

    @Spy
    List<Task>  taskList = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        taskList = getTaskList();
    }

    @Test
    public void addTask() {
        doNothing().when(taskDAO).addTask(any(Task.class));
        taskService.addTask(any(Task.class));
        verify(taskDAO,atLeastOnce()).addTask(any(Task.class));
    }

    @Test
    public void updateTask() {
        Task task= taskList.get(0);
        when(taskDAO.updateTask(anyObject())).thenReturn(task);
        taskService.updateTask(anyObject());
        verify(taskDAO,atLeastOnce()).updateTask(anyObject());
    }

    @Test
    public void deleteTask() {
        doNothing().when(taskDAO).deleteTask(anyInt());
        taskService.deleteTask(anyInt());
        verify(taskDAO,atLeastOnce()).deleteTask(anyInt());
    }

    @Test
    public void getAllTasks(){
        when(taskDAO.getAllTask()).thenReturn(taskList);
        Assert.assertEquals(taskService.getAllTasks(),taskList);
    }

    @Test
    public void getTask() {
        Task task = taskList.get(0);
        when(taskDAO.getTask(anyInt())).thenReturn(task);
        taskService.getTask(anyInt());
        verify(taskDAO,atLeastOnce()).getTask(anyInt());
    }

    public List<Task> getTaskList() {
        List<Task> taskList = new ArrayList<>();
        Task task = new Task();
        task.setTask_desc("Task Description");
        task.setTask_priority("HIGH");
        Task task1 = new Task();
        task1.setTask_priority("LOW");
        task1.setTask_desc("LOW Task Description");
        taskList.add(task);
        taskList.add(task1);
        return taskList;
    }
}

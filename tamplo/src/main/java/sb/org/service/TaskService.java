package sb.org.service;

import sb.org.model.Task;

import java.util.List;

public interface TaskService {

    public void addTask(Task task);

    public List<Task> getAllTasks();

    public void deleteTask(Integer taskId);

    public Task getTask(int taskId);

    public Task updateTask(Task task);

    /*public List<Task> getTaskById(int employeeId);*/
}

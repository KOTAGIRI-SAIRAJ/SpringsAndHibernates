package sb.org.dao;

import sb.org.model.Task;

import java.util.List;

public interface TaskDAO {

    public void addTask(Task task);

    public List<Task> getAllTask();

    public void deleteTask(Integer taskId);

    public Task updateTask(Task task);

    public Task getTask(int taskId);

    /*public List<Task> getTaskById(int employeeId);*/
}

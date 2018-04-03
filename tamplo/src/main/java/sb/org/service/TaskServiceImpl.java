package sb.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sb.org.dao.TaskDAO;
import sb.org.model.Task;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Override
    @Transactional
    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    @Override
    @Transactional
    public List<Task> getAllTasks() {
        return taskDAO.getAllTask();
    }

    @Override
    @Transactional
    public void deleteTask(Integer taskId) {
        taskDAO.deleteTask(taskId);
    }

    @Override
    @Transactional
    public Task getTask(int taskId) {
        return  taskDAO.getTask(taskId);
    }

    @Override
    @Transactional
    public Task updateTask(Task task) {
        return taskDAO.updateTask(task);
    }
}

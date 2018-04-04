package sb.org.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sb.org.model.Task;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTask(Task task) {
        sessionFactory.getCurrentSession().saveOrUpdate(task);
    }

    @Override
    public List<Task> getAllTask() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
        criteria.setMaxResults(10)
                .addOrder(Order.desc("task_priority"));
        System.out.println("Size of Tasks "+criteria.list().size());
        return criteria.list();
    }

    @Override
    public void deleteTask(Integer taskId) {
        Task task = (Task) sessionFactory.getCurrentSession().load(
                Task.class, taskId
        );
        if (null != task) {
            this.sessionFactory.getCurrentSession().delete(task);
        }
    }

    @Override
    public Task updateTask(Task task) {
        sessionFactory.getCurrentSession().update(task);
        return task;
    }

    @Override
    public Task getTask(int taskId) {
        return (Task) sessionFactory.getCurrentSession().get(Task.class, taskId);
    }

    /*@Override
    public List<Task> getTaskById(int employeeId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
        criteria.add(Restrictions.eq("employee_id",employeeId));
        System.out.println("Size of Tasks "+criteria.list().size());
        return criteria.list();
    }*/
}

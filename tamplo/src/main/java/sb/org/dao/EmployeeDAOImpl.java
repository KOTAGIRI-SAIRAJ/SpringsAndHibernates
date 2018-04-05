package sb.org.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sb.org.model.Employee;
import sb.org.model.Task;

import java.util.Iterator;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addEmployee(Employee employee) {
        employee = setEmployeeToTasks(employee);
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);
        criteria.setMaxResults(10)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder( Order.desc("name") );
        return criteria.list();
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee = (Employee) sessionFactory.getCurrentSession().load(
                Employee.class, employeeId);
        if (null != employee) {
            this.sessionFactory.getCurrentSession().delete(employee);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employee = setEmployeeToTasks(employee);
        sessionFactory.getCurrentSession().update(employee);
        return employee;
    }

    @Override
    public Employee getEmployee(int employeeId) {
        return (Employee) sessionFactory.getCurrentSession().get(
                Employee.class, employeeId);
    }

    public Employee setEmployeeToTasks(Employee employee){
        List<Task> tasks = employee.getTasks();
        Iterator<Task> taskIterator = tasks.iterator();
        while (taskIterator.hasNext()){
            Task task = taskIterator.next();
            task.setEmployee(employee);
        }
        return employee;
    }
}

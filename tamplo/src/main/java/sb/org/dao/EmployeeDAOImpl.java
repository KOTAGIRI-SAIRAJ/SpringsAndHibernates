package sb.org.dao;

import org.hibernate.*;
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
        /*Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("+  getAllEmployeeDetails");
        List<Employee> NamedQueryemployeeList = query.list();
        System.out.println("addEmployee without Native"+NamedQueryemployeeList.size());
        Query query =  session.getNamedQuery("getAllEmployees");
        List<Employee> NativeNamedQueryemployeeList = query.list();
        System.out.println("addEmployee with Native"+NativeNamedQueryemployeeList.size());*/
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        Employee employee = (Employee) sessionFactory.getCurrentSession().load(
                Employee.class, employeeId);
        /*Hibernate.initialize(employee.getMeetings());
        System.out.println("deleteEmployee called in DAOImpl");
        List<Meeting> meetingList = employee.getMeetings();
        System.out.println("meetingList "+ meetingList.size());
        employee.setMeetings(null);
        sessionFactory.getCurrentSession().update(employee);*/
        /*if (null != employee) {*/
            this.sessionFactory.getCurrentSession().delete(employee);
        /*}*/
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

    @Override
    public List<Employee> getUnEnrolledEmployees(List employeeIdsList) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class)
                .add(Restrictions.not(Restrictions.in("id", employeeIdsList)))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public List<Employee> searchForEmployee(String keyword) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Employee.class);
        if (keyword.matches("[0-9]+") && keyword.length() > 2 && keyword.length() < 7) {
            int value = Integer.parseInt(keyword);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .add(Restrictions.eq("salary",value));
        } else {
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .add(Restrictions.or(
                            Restrictions.like("name","%"+keyword+"%").ignoreCase(),
                            (Restrictions.like("telephone","%"+keyword+"%").ignoreCase()),
                            (Restrictions.like("department","%"+keyword+"%").ignoreCase()),
                            (Restrictions.like("email","%"+keyword+"%").ignoreCase())
                    ));
        }
        return criteria.list();
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

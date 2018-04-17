package sb.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sb.org.dao.EmployeeDAO;
import sb.org.model.Employee;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void deleteEmployee(Integer employeeId) {
        employeeDAO.deleteEmployee(employeeId);
    }

    @Override
    @Transactional
    public Employee getEmployee(int employeeId) {
        Employee employee = employeeDAO.getEmployee(employeeId);
        //Hibernate.initialize(employee.getMeetings());
        return employee;
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        return employeeDAO.updateEmployee(employee);
    }

    @Override
    public List<Employee> getUnEnrolledEmployees(List employees) {
        List<Employee> employeeList = employeeDAO.getUnEnrolledEmployees(employees);
        return employeeList;
    }

    @Override
    public List<Employee> searchForEmployee(String keyword) {
        return employeeDAO.searchForEmployee(keyword);
    }
}

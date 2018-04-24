package sb.org.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import sb.org.model.Employee;
import org.springframework.validation.Validator;
import sb.org.model.Task;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {

        ValidationUtils.rejectIfEmpty(err, "name", "employee.name.empty");
        ValidationUtils.rejectIfEmpty(err, "email", "employee.email.empty");
        ValidationUtils.rejectIfEmpty(err, "salary", "employee.salary.empty");
        ValidationUtils.rejectIfEmpty(err, "department", "employee.department.empty");
        ValidationUtils.rejectIfEmpty(err, "telephone", "employee.telephone.empty");

        ValidationUtils.rejectIfEmpty(err, "accessCard.card_holder_name", "accesscard.name.empty");
        ValidationUtils.rejectIfEmpty(err, "accessCard.department", "accesscard.department.empty");
        ValidationUtils.rejectIfEmpty(err, "accessCard.organization", "accesscard.organisation.empty");

        Employee employee= (Employee) obj;
        List<Task> taskList = employee.getTasks();

        List<ObjectError> errors = err.getAllErrors();

        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        Pattern namePattern = Pattern.compile("^[a-zA-Z ]*$",Pattern.CASE_INSENSITIVE);
        Pattern numberPattern = Pattern.compile("^[0-9]*$");
        Pattern telephoneLength = Pattern.compile("^[0-9]{10}$");

        int counter = 0;
        for (Task task : taskList) {
            ValidationUtils.rejectIfEmpty(err, "tasks["+counter+"].task_priority", "task.priority.empty");
            ValidationUtils.rejectIfEmpty(err, "tasks["+counter+"].task_desc", "task.description.empty");

            if (!(namePattern.matcher(task.getTask_priority()).matches())) {
                err.rejectValue("tasks["+counter+"].task_priority", "task.priority.invalid");
            }
            if (!(namePattern.matcher(task.getTask_desc()).matches())) {
                err.rejectValue("tasks["+counter+"].task_desc", "task.description.invalid");
            }
            counter++;
        }

        /*for (ObjectError error : errors) {
            System.out.println("--------------------");
            System.out.println("Code "+error.getCode());
            System.out.println("Object Name "+error.getObjectName());
            int checkExits =0;
            if((error.getCode().equals("employee.email.empty")) && !(emailPattern.matcher(employee.getEmail()).matches())) {
                err.rejectValue("email", "employee.email.invalid");
            }
        }*/

        if (!(emailPattern.matcher(employee.getEmail()).matches())) {
            err.rejectValue("email", "employee.email.invalid");
        }
        if (!(namePattern.matcher(employee.getName()).matches())) {
            err.rejectValue("name", "employee.name.invalid");
        }
        if (!(namePattern.matcher(employee.getDepartment()).matches())) {
            err.rejectValue("department", "employee.department.invalid");
        }
        if (!(namePattern.matcher(employee.getDepartment()).matches())) {
            err.rejectValue("accessCard.card_holder_name", "accesscard.name.invalid");
        }
        if (!(namePattern.matcher(employee.getDepartment()).matches())) {
            err.rejectValue("accessCard.department", "accesscard.department.invalid");
        }
        if (!(namePattern.matcher(employee.getDepartment()).matches())) {
            err.rejectValue("accessCard.organization", "accesscard.organisation.invalid");
        }
        if(employee.getSalary() == null){
            if (!(numberPattern.matcher("").matches())) {
                err.rejectValue("salary", "employee.salary.invalid");
            }
        } else {
            if (!(numberPattern.matcher(String.valueOf(employee.getSalary())).matches())) {
                err.rejectValue("salary", "employee.salary.invalid");
            }
        }

        if (!(telephoneLength.matcher(employee.getTelephone()).matches())) {
            err.rejectValue("telephone", "employee.telephone.invalid");
        }
    }
}

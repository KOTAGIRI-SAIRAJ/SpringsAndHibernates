package sb.org.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    @NotNull
    private String email;

    @Column
    @Min(10000)
    private Integer salary;

    @Column
    private String telephone;

    @Column
    private String department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "access_card_id")
    private AccessCard accessCard;

    /*@OneToOne(mappedBy="student", cascade = CascadeType.ALL)
    private Address address;*/

    @OneToMany(mappedBy="employee",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Task> tasks;


    @ManyToMany(targetEntity = sb.org.model.Meeting.class,cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Employee_Meeting",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "meeting_id") }
    )
    List<Meeting> meetings= new ArrayList<Meeting>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public String getDepartment() {  return department;  }

    public void setDepartment(String department) {  this.department = department;   }

    public AccessCard getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(AccessCard accessCard) {
        this.accessCard = accessCard;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

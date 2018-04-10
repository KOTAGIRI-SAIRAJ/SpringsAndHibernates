package sb.org.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String meeting_title;

    @Column
    private int meeting_dur;

    @Column
    private String client_name;

    /*@ManyToMany(targetEntity = sb.org.model.Employee.class,cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Employee_Meeting",
            joinColumns = { @JoinColumn(name = "meeting_id") },
            inverseJoinColumns = { @JoinColumn(name = "employee_id") }
    )*/


    @ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST},targetEntity = Employee.class)
    @JoinTable(name="Employee_Meeting", joinColumns={@JoinColumn(name="meeting_id",nullable = false,updatable = false)}, inverseJoinColumns = {@JoinColumn(name="employee_id",nullable = false,updatable = false)},
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<Employee> employees = new ArrayList<Employee>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeeting_title() {
        return meeting_title;
    }

    public void setMeeting_title(String meeting_title) {
        this.meeting_title = meeting_title;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getMeeting_dur() {
        return meeting_dur;
    }

    public void setMeeting_dur(int meeting_dur) {
        this.meeting_dur = meeting_dur;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }
}

package sb.org.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String meeting_title;

    /*@ManyToMany(mappedBy = "employee")
    private List<Employee> employeeList = new ArrayList<Employee>();*/

    @ManyToMany(mappedBy = "meetings")
    private List<Employee> employees = new ArrayList<Employee>();
}

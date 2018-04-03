package sb.org.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "access_card_id")
    private AccessCard accessCard;

    @OneToMany(mappedBy="employee")
    private Set<Task> tasks;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Employee_Meeting",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "meeting_id") }
    )
    List<Meeting> meetings= new ArrayList<Meeting>();
}

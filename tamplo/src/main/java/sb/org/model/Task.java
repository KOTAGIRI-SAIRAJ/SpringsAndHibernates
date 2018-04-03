package sb.org.model;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String task_desc;

    @Column
    private String task_priority;


    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee;
}

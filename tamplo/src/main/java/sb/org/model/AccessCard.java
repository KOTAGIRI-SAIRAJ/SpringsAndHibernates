package sb.org.model;

import javax.persistence.*;

@Entity
@Table(name = "access_card")
public class AccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String card_holder_name;

    @Column
    private String department;
}

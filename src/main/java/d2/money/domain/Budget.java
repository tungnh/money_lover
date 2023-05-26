package d2.money.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "amount")
    private double amount;
    @Column(name = "spent")
    private double spent;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "choose_period")
    private String choosePeriod;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "budget_category", joinColumns = @JoinColumn(name = "budget_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList = new ArrayList<>();
}
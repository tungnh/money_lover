package d2.money.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "wallet_id")
    private Integer walletId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "day")
    private Date day;
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Wallet wallet;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "transaction_with_user", joinColumns = @JoinColumn(name = "transaction_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userList = new ArrayList<>();
}
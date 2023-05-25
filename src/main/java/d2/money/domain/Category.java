package d2.money.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "name")
    private String name;
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;
    @Column(name = "type")
    private String type;
    @Column(name = "status")
    private boolean status;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
    @Column(name = "parent_id")
    private int parentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Transaction> transactionList = new ArrayList<>();
}

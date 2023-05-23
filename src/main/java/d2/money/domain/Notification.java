package d2.money.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @Column(name = "title")
    private String title;
    @Column(name = "type")
    private int type;
    @Column(name = "message",columnDefinition = "TEXT",nullable = false)
    private String message;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    @Column(name = "last_modified_date")
    private Date  lastModifiedDate;
    @Column(name = "is_read")
    private boolean  isRead;
    @Column(name = "readed_at")
    private Date readedAt;
}

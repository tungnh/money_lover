package d2.money.domain;

import javax.persistence.*;

@Entity
@Table(name = "friend")
public class Friend {
    @EmbeddedId
    private FriendId id;

    @Column(name = "state")
    private int state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id", insertable = false, updatable = false)
    private User friend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Friend() {
    }
}
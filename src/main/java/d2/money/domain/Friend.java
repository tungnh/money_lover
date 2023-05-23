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

    public Friend(FriendId id, int state, User friend, User user) {
        this.id = id;
        this.state = state;
        this.friend = friend;
        this.user = user;
    }

    public FriendId getId() {
        return id;
    }

    public void setId(FriendId id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
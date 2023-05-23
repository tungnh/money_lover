package d2.money.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public  class FriendId implements Serializable {
    @Column(name = "user_id")
    private int userId;

    @Column(name = "friend_id")
    private int friendId;

    public FriendId() {
    }

    public FriendId(int userId, int friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    // Getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    // Implement equals() and hashCode() methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendId friendId1 = (FriendId) o;

        if (userId != friendId1.userId) return false;
        return friendId == friendId1.friendId;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + friendId;
        return result;
    }
}
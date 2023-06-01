package d2.money.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FriendId implements Serializable {
    @Column(name = "user_id")
    private int userId;
    @Column(name = "friend_id", updatable = false)
    private int friendId;

    public FriendId() {
    }

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
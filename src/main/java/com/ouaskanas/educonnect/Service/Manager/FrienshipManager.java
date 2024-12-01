package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.Friendship;

import java.util.List;

public interface FrienshipManager {
    public List<Friendship> friendshipListforUser(long user_id);
    public Friendship sendFriendShipRequest(long user_id, long friend_id);
    public Friendship acceptFriendshipRequest(long friendship_id);
    public void declineFriendshipRequest(long friendship_id);

}

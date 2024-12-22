package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.Friendship;

import java.util.List;

public interface FrienshipManager {
    public List<Friendship> friendshipListforUser(int user_id);
    public Friendship sendFriendShipRequest(int user_id, int friend_id);
    public Friendship acceptFriendshipRequest(int friendship_id);
    public void declineFriendshipRequest(int friendship_id);

}

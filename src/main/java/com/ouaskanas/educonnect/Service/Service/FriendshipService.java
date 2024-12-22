package com.ouaskanas.educonnect.Service.Service;

import com.ouaskanas.educonnect.Dao.Entities.FriendShipStatus;
import com.ouaskanas.educonnect.Dao.Entities.Friendship;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.FriendshipRepository;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import com.ouaskanas.educonnect.Service.Manager.FrienshipManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService implements FrienshipManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Override
    public List<Friendship> friendshipListforUser(int user_id) {
        User user = userRepository.findById(user_id).get();
        return user.getFriendships();
    }


    @Override
    public Friendship sendFriendShipRequest(int user_id, int friend_id) {
        Friendship friendship = Friendship.builder()
                .student(userRepository.findById(user_id).get())
                .friend(userRepository.findById(friend_id).get())
                .status(FriendShipStatus.PENDING)
                .build();
        return friendshipRepository.save(friendship);
    }

    @Override
    public Friendship acceptFriendshipRequest(int friendship_id) {
        var friendship = friendshipRepository.findById(friendship_id).get();
        friendship.setStatus(FriendShipStatus.ACCEPTED);
        return friendshipRepository.save(friendship);
    }

    @Override
    public void declineFriendshipRequest(int friendship_id) {
        friendshipRepository.deleteById(friendship_id);
    }
}

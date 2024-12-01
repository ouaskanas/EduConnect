package com.ouaskanas.educonnect.Web;

import com.ouaskanas.educonnect.Dao.Entities.Friendship;
import com.ouaskanas.educonnect.Service.Service.FriendshipService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friendship")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @GetMapping("/friendshiplist")
    public List<Friendship> getFriendshipList(){
        //AuthenticationPrincipals
        return List.of();
    }

//    @PostMapping("/sendfriendship")
//    public ResponseEntity<Friendship> sendFriendship(@RequestBody ){
//
//        return null;
//    }


}

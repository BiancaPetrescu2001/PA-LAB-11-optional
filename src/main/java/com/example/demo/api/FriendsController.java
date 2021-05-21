package com.example.demo.api;


import com.example.demo.model.Friends;
import com.example.demo.service.FriendsService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/friends")
@RestController

public class FriendsController {

    private  FriendsService friendsService;
    @Autowired
    private FriendsRepository friendsRepository;

    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @PostMapping
    public @ResponseBody Friends addFriends(@NonNull @RequestBody Friends friends) {
        friendsService.addFriends(friends);
        friendsRepository.save(friends);
        return friends;
    }

    @GetMapping
    public List<Friends> getAllFriends() {
        return (List<Friends>) friendsRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Optional<Friends> getFriendsById(@PathVariable("id") int id) {
        return friendsRepository.findById(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteFriendsById(@PathVariable("id") int id) {
        friendsService.deleteFriends(id);
        if(friendsRepository.existsById(id)){
            Friends friends = friendsRepository.findById(id).get();
            friendsRepository.deleteById(id);
        }
    }

    @PutMapping(path = "{id}")
    public void updateFriends(@PathVariable("id") int id, @NonNull @RequestBody Friends friendsToUpdate) {
        friendsService.updateFriends(id, friendsToUpdate);
        if(friendsRepository.existsById(id)){
            Friends friends = friendsRepository.findById(id).get();
            friends.setFriendId(friendsToUpdate.getFriendId());
            friendsRepository.save(friends);
        }
    }

}

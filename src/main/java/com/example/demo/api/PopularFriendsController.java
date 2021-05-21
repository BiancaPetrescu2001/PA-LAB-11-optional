package com.example.demo.api;


import com.example.demo.model.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/friends")
@RestController

public class PopularFriendsController {

    @Autowired
    private FriendsRepository friendsRepository;
    @Autowired
    private PersonRepository personRepository;
    private Map<Integer, Integer> numberOfFriends = new HashMap<>();

    public boolean isInMapOfFriends(int id) {
        for (Map.Entry<Integer, Integer> entry : numberOfFriends.entrySet()) {
            if (entry.getKey().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void getNumberOfFriends() {
        List<Friends> listOfFriends = (List<Friends>) friendsRepository.findAll();
        for (int i = 0; i < listOfFriends.size(); i++) {
            if (!isInMapOfFriends(listOfFriends.get(i).getId())) {
                numberOfFriends.put(listOfFriends.get(i).getId(), 1);
            } else {
                numberOfFriends.put(listOfFriends.get(i).getId(), numberOfFriends.get(listOfFriends.get(i).getId()) + 1);
            }
            if (!isInMapOfFriends(listOfFriends.get(i).getFriendId())) {
                numberOfFriends.put(listOfFriends.get(i).getFriendId(), 1);
            } else {
                numberOfFriends.put(listOfFriends.get(i).getFriendId(), numberOfFriends.get(listOfFriends.get(i).getFriendId()) + 1);
            }
        }
    }

    @GetMapping(path = "/mostPopular/{k}")
    public String getMostPopularFriends(@PathVariable("k") int k) {
        getNumberOfFriends();
        String message = "The first " + k + " most connected (popular) persons in the network: ";
        Map<Integer, Integer> sortedMapWithFriends = new LinkedHashMap<>();
        sortedMapWithFriends =
                numberOfFriends.entrySet()
                        .stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        int contor = 0;
        for (Map.Entry<Integer, Integer> entry : sortedMapWithFriends.entrySet()) {
            if (contor < k) {
                message += (personRepository.findById(entry.getKey()).get().getName());
                message += " ";
                contor++;
            }
            else{
                break;
            }
        }
        return message;
    }

    @GetMapping(path = "/leastPopular/{k}")
    public String getLeastPopularFriends(@PathVariable("k") int k) {
        getNumberOfFriends();
        String message = "The first " + k + " least connected (popular) persons in the network: ";
        Map<Integer, Integer> sortedMapWithFriends = new LinkedHashMap<>();
        sortedMapWithFriends =
                numberOfFriends.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        int contor = 0;
        for (Map.Entry<Integer, Integer> entry : sortedMapWithFriends.entrySet()) {
            if (contor < k) {
                message += (personRepository.findById(entry.getKey()).get().getName());
                message += " ";
                contor++;
            }
            else{
                break;
            }
        }
        return message;
    }
}


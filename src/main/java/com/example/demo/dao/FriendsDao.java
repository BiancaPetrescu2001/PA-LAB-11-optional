package com.example.demo.dao;

import com.example.demo.model.Friends;

import java.util.List;
import java.util.Optional;

public interface FriendsDao {

    int insertFriends(int id, int friendId);

    default int insertFriends(Friends friends){
        return insertFriends(friends.getId(), friends.getFriendId());
    }

    List<Friends> selectAllFriends();
    Optional<Friends> selectFriendsById(int id);

    int deleteFriendsById(int id);

    int updateFriendsById(int id, Friends update);
}

package com.example.demo.service;

import com.example.demo.dao.FriendsDao;
import com.example.demo.model.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {

    private final FriendsDao friendsDao;

    @Autowired
    public FriendsService(@Qualifier("friendsDao") FriendsDao friendsDao) {
        this.friendsDao = friendsDao;
    }

    public int addFriends(Friends friends){
        return friendsDao.insertFriends(friends);
    }

    public List<Friends> getAllFriends(){
        return friendsDao.selectAllFriends();
    }

    public Optional<Friends> getFriendsById(int id){
        return friendsDao.selectFriendsById(id);
    }

    public int deleteFriends(int id){
        return friendsDao.deleteFriendsById(id);
    }

    public int updateFriends(int id, Friends newFriends){
        return friendsDao.updateFriendsById(id, newFriends);
    }
}

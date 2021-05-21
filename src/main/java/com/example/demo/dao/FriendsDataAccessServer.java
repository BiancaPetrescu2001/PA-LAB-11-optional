package com.example.demo.dao;

import com.example.demo.model.Friends;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("friendsDao")
public class FriendsDataAccessServer implements FriendsDao {
    private static List<Friends> DB = new ArrayList<>();

    @Override
    public int insertFriends(@Param("id") int id, @Param("id") int friendId) {
        DB.add(new Friends(id, friendId));
        return 1;
    }

    @Override
    public List<Friends> selectAllFriends() {
        return DB;
    }

    @Override
    public Optional<Friends> selectFriendsById(int id) {
        return DB.stream()
                .filter(friend->friend.getId()==id)
                .findFirst();
    }

    @Override
    public int deleteFriendsById(int id) {
        Optional<Friends> friendsMaybe = selectFriendsById(id);
        if(friendsMaybe.isPresent()){
            DB.remove(friendsMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateFriendsById(int id, Friends update) {
        return selectFriendsById(id)
                .map(person->{
                    int indexOfPersonUpdate = DB.indexOf(person);
                    if(indexOfPersonUpdate>=0){
                        DB.set(indexOfPersonUpdate, new Friends(id,update.getFriendId()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}

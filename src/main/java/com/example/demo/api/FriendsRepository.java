package com.example.demo.api;

import com.example.demo.model.Friends;
import org.springframework.data.repository.CrudRepository;

public interface FriendsRepository extends CrudRepository<Friends, Integer> {
}

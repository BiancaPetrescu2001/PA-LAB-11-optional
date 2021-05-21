package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@ToString
@EqualsAndHashCode
@Entity
@Table(name="FRIENDS")

public class Friends {

    @Id
    private int id;
    private int friend_id;

    public Friends(@JsonProperty("id") int id,
                   @JsonProperty("friend_id") int friend_id) {
        this.id = id;
        this.friend_id = friend_id;
    }

    public Friends() {

    }

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name="friend_id")
    public int getFriendId() {
        return friend_id;
    }

    public void setFriendId(int friend_id) {
        this.friend_id = friend_id;
    }
}

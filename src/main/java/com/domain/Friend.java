package com.domain;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Collection;

public class Friend {
    private final Collection<Friend> friends;
    private final String email;

    public Friend(String email) {
        this.email = email;
        this.friends = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public Collection<Friend> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("email", email)
            .toString();
    }

    public void addFriendship(Friend friend) {
        friends.add(friend);
        friend.getFriends().add(this);
    }
}

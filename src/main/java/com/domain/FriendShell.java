package com.domain;

import com.google.common.base.MoreObjects;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FriendShell {

    private final Friend friend;

    protected HashMap<String, Friend> friendsLayerPrev = new HashMap<>();
    protected HashMap<String, Friend> friendsLayerCurrent = new HashMap<>();
    protected HashMap<String, Friend> friendsLayerNext = new HashMap<>();

    public FriendShell(Friend friend) {
        this.friend = friend;
        friendsLayerNext.put(friend.getEmail(), friend);
    }

    public void spreadShell() {
        friendsLayerPrev.clear();
        friendsLayerPrev.putAll(friendsLayerCurrent);
        friendsLayerCurrent.clear();
        friendsLayerCurrent.putAll(friendsLayerNext);
        friendsLayerNext.clear();
        friendsLayerNext.putAll(
            friendsLayerCurrent.entrySet()
                .stream()
                .flatMap(es -> es.getValue()
                    .getFriends()
                    .stream()
                    .filter(f -> {
                        final var email = f.getEmail();
                        return !friendsLayerCurrent.containsKey(email) && !friendsLayerPrev.containsKey(email);
                    })
                    .map(f -> new AbstractMap.SimpleEntry<>(f.getEmail(), f))
                )
                .collect(
                    Collectors.toMap(
                        es -> ((Map.Entry<String, Friend>) es).getKey(),
                        es -> ((Map.Entry<String, Friend>) es).getValue(),
                        (k1, k2) -> k1
                    )
                )
        );
        if (friendsLayerCurrent.isEmpty()) {
            throw new IllegalStateException(String.format("All fiends of %s were sorted out", friend));
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("friend", friend)
            .add("friendsLayerPrev", friendsLayerPrev)
            .add("friendsLayerCurrent", friendsLayerCurrent)
            .add("friendsLayerNext", friendsLayerNext)
            .add("parent", super.toString())
            .toString();
    }

    public HashMap<String, Friend> getFriendsLayerCurrent() {
        return friendsLayerCurrent;
    }

    public HashMap<String, Friend> getFriendsLayerNext() {
        return friendsLayerNext;
    }
}

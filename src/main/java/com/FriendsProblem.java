package com;

import com.domain.Friend;
import com.domain.FriendShell;

public class FriendsProblem {
    public static void main(String[] args) {

        Friend a = new Friend("A");
        Friend b = new Friend("B");
        Friend c = new Friend("C");
        Friend d = new Friend("D");
        Friend e = new Friend("E");
        Friend f = new Friend("F");
        Friend g = new Friend("G");
        Friend h = new Friend("H");
        Friend i = new Friend("I");
        Friend j = new Friend("J");
        Friend k = new Friend("K");
        Friend l = new Friend("L");
        Friend m = new Friend("M");
        Friend n = new Friend("N");
        Friend o = new Friend("O");

        a.addFriendship(b);
        a.addFriendship(c);
        a.addFriendship(d);
        d.addFriendship(e);
        d.addFriendship(f);
        f.addFriendship(h);
        g.addFriendship(e);
        h.addFriendship(l);
        i.addFriendship(e);
        j.addFriendship(i);
        k.addFriendship(j);
        l.addFriendship(k);

        final var friendShell1 = new FriendShell(a);
        final var friendShell2 = new FriendShell(g);

        System.out.println(isIntersected(friendShell1, friendShell2));

    }

    public static Boolean isIntersected(FriendShell friendShell1, FriendShell friendShell2) {
        final var friendsLayerCurrent1 = friendShell1.getFriendsLayerCurrent();
        final var friendsLayerNext1 = friendShell1.getFriendsLayerNext();
        final var friendsLayerCurrent2 = friendShell2.getFriendsLayerCurrent();
        var pathExists = false;
        do {
            friendShell1.spreadShell();
            friendShell2.spreadShell();
            pathExists = friendsLayerCurrent2
                .entrySet()
                .stream()
                .anyMatch(es ->
                    friendsLayerCurrent1.containsKey(es.getKey())
                    || friendsLayerNext1.containsKey(es.getKey())
                );
        } while (!pathExists);
        return true;
    }
}
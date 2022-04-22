package com;

import com.domain.Friend;
import com.domain.FriendShell;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FriendsProblem {
    public static void main(String[] args) {
//        SpringApplication.run(FriendsProblem.class, args)
//            .close();

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
//        e.addFriendship(g);
        f.addFriendship(h);
//        g.addFriendship(e);
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
        var pathExists = false;
        var friendsLayer1 = friendShell1.getFriendsLayerCurrent();
        var friendsLayer2 = friendShell2.getFriendsLayerCurrent();
        while (!pathExists) {
            pathExists = friendsLayer1
                .entrySet()
                .stream()
                .anyMatch(es -> friendsLayer2.containsKey(es.getKey()));
            friendShell1.spreadShell();
            friendShell2.spreadShell();
        }
        return true;
    }
}
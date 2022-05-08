# FriendsProblem

Given a pair of friends in a cloud of friends. Needs to
determine whether they can contact each other through a chain of friends.
```java
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
```

P.S.: a friendship is mutual always and the mail field in class is unique for any friend.
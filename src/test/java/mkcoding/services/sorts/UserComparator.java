package mkcoding.services.sorts;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by mx on 16/9/7.
 */
public class UserComparator implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getAge() - o2.getAge();
    }

    public static void main(String[] args) {
        User[] users = new User[]{new User(60, "a"), new User(30, "b")};
        Arrays.sort(users, new UserComparator());

        for (User user : users) {
            System.out.println(user.getId() + " " + user.getAge());
        }
    }
}

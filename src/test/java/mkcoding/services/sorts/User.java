package mkcoding.services.sorts;

/**
 * Created by mx on 16/9/7.
 */
public class User {

    private String id;
    private int age;

    public User(int age, String id) {
        this.age = age;
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

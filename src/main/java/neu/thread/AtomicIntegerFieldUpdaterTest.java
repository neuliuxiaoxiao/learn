package neu.thread;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Title AtomicIntegerFieldUpdaterTest
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/10 15:44
 **/
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        User user = new User("Java", 22);
        System.out.println(a.getAndIncrement(user));
        System.out.println(a.get(user));
    }
}

class User {
    private String name;
    public volatile int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

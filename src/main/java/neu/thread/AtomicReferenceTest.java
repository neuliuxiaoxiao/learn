package neu.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title AtomicReferenceTest
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/10 15:41
 **/
public class AtomicReferenceTest {

    public static void main(String[] args) {
        AtomicReference<Person> ar = new AtomicReference<>();
        Person person = new Person("demo", 12);
        ar.set(person);
        Person update = new Person("test", 12);
        ar.compareAndSet(person, update);
        System.out.println(ar.get().getName());
        System.out.println(ar.get().getAge());
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
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

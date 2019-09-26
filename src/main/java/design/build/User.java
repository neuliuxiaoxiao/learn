package design.build;

/**
 * @Title User
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 13:51
 **/
public class User {

    private int age;
    private String name;
    private String addr;
    private String salary;

    private User(int age, String name, String addr, String salary) {
        this.age = age;
        this.name = name;
        this.addr = addr;
        this.salary = salary;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }
    public static class UserBuilder {
        private int age;
        private String name;
        private String addr;
        private String salary;

        public UserBuilder(){
        }

        public UserBuilder age(){
            this.age=age;
            return this;
        }
        public UserBuilder name(String name){
            this.name=name;
            return this;
        }
        public UserBuilder addr(String addr){
            this.addr=addr;
            return this;
        }
        public UserBuilder salary(String salary){
            this.salary=salary;
            return this;
        }
        public User build(){
            return  new User(age,name,addr,salary);
        }
    }


}


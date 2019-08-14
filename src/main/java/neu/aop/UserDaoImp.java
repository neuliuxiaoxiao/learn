package neu.aop;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements UserDao {
    public int addUser() {
        System.out.println("add user ......");
        return 6666;
    }

    public void updateUser() {
        System.out.println("update user ......");
    }

    public void deleteUser() {
        System.out.println("delete user ......");
    }

    public void findUser() {
        System.out.println("find user ......");
    }
}

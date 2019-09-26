package design.build;

/**
 * @Title App
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 13:55
 **/
public class App {

     public static void main(String[] args) {

         User u = User.builder().age().name("tom").addr("123").build();
      }
}

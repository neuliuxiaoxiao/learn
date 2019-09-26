package design.factory;

/**
 * @Title Client
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:26
 **/
public class Client {

     public static void main(String[] args) {
         ChineseFactory fc  = new ChineseFactory();
         fc.make("A").eat();
      }
}

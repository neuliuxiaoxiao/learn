package design.factory.simple;

/**
 * @Title Client
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:15
 **/
public class Client {
    public static void main(String[] args) {
         FoodFactory fc = new FoodFactory();
         fc.makeFood("meat").eat();
    }
}

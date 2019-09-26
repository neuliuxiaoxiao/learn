package design.bridge;

/**
 * @Title Client
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 14:27
 **/
public class Client {

     public static void main(String[] args) {
         Shape greenCircle = new Circle(10,new GreenPen());
         greenCircle.draw();
      }
}

package design.factory.abstraction;

/**
 * @Title Client
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:37
 **/
public class Client {

    public static void main(String[] args) {
        ComputerFactory fc = new InterFactory();
        fc.makeCpu();
        fc.makeBoard();
    }

}

package design.bridge;

/**
 * @Title RedPen
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 14:22
 **/
public class GreenPen implements DrawApi{

    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("ÂÌ±Ê»¨");
    }
}

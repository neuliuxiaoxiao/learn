package design.bridge;

/**
 * @Title Circle
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 14:26
 **/
public class Circle extends Shape {

    private int radius;
    public Circle(int radius, DrawApi drawApi) {
        super(drawApi);
        this.radius = radius;
    }
    @Override
    public void draw() {
        drawApi.draw(radius, 0, 0);
    }
}

package design.bridge;

/**
 * @Title Shape
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 14:25
 **/
public abstract class Shape {
    protected DrawApi drawApi;
    protected Shape(DrawApi drawApi) {
        this.drawApi = drawApi;
    }
    public abstract void draw();
}

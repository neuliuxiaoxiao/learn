package design.decorator;

/**
 * @Title BlackTea
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 14:35
 **/
public class BlackTea extends Beverage {

    @Override
    public String getDescription() {
        return "ºì²è";
    }
    @Override
    public double cost() {
        return 10;
    }
}

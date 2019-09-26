package design.decorator;

/**
 * @Title GreenTea
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 14:35
 **/
public class GreenTea extends Beverage {
    @Override
    public String getDescription() {
        return "бл╡Х";
    }
    @Override
    public double cost() {
        return 11;
    }
}
package design.factory;

import design.factory.simple.Food;

/**
 * @Title ChineseFoodA
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:22
 **/
public class ChineseFoodB implements Food {

    @Override
    public void eat() {
        System.out.println("中国食品A好吃");
    }
}

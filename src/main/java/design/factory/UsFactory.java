package design.factory;

import design.factory.simple.Food;

/**
 * @Title ChineseFactory
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:25
 **/
public class UsFactory {

    public Food make(String name){
        if ("A".equals(name)){
            return new UsFoodA();
        }else {
            return new UsFoodB();
        }
    }

}

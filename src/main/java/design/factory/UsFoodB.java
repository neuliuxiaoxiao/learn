package design.factory;

import design.factory.simple.Food;

/**
 * @Title ChineseFoodA
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:22
 **/
class UsFoodB implements Food {

    @Override
    public void eat() {
        System.out.println("USʳƷB�ó�");
    }
}

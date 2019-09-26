package design.factory.simple;

/**
 * @Title FoodFactory
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/11 11:14
 **/
public class FoodFactory {

    public Food makeFood(String name){
        if (name.equals("fruit")){
            return new FruitFood();
        }else {
            return new MeatFood();
        }
    }
}

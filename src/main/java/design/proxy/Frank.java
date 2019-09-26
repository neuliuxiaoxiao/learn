package design.proxy;

/**
 * @Title Frank
 * @Description TODO
 * @Author liuxi58
 * @Date 2019/9/26 8:27
 **/
public abstract class Frank {

    public void muban(){
        login();
        play("123");
        buy();
    }

    protected abstract void login();

    public String play(String name){
        System.out.println("frank name");
        return  name;
    }

    public String buy(){
        System.out.println("frank buy");
        return "buy";
    }
}
